package ru.kvvartet.lndclient.states.manager;

import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.states.manager.requests.StateClearRequest;
import ru.kvvartet.lndclient.states.manager.requests.StateManagerRequest;
import ru.kvvartet.lndclient.states.manager.requests.StatePopRequest;
import ru.kvvartet.lndclient.states.manager.requests.StatePushRequest;
import ru.kvvartet.lndclient.states.state.GameState;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class StateStack implements StateManager {
    private final Deque<GameState> states = new ArrayDeque<>();
    private final Queue<StateManagerRequest> pendingRequests = new ArrayDeque<>();

    @Override
    public void requestStatePop() {
        pendingRequests.offer(new StatePopRequest());
    }

    @Override
    public void requestStateClear() {
        pendingRequests.offer(new StateClearRequest());
    }

    @Override
    public void requestStatePush(@NotNull GameState requestedState) {
        pendingRequests.offer(new StatePushRequest(requestedState));
    }

    @Override
    public void update(float timeDelta) {
        for (GameState state : states) {
            if (!state.update(timeDelta)) {
                break;
            }
        }
        processRequests();
    }

    @Override
    public void handleInput() {

    }

    private void processRequests() {
        while (!pendingRequests.isEmpty()) {
            final StateManagerRequest request = pendingRequests.poll();
            processRequest(request);
        }
    }

    private void processRequest(@NotNull StateManagerRequest request) {
        if (request == null) {
            return;
        }
        if (request.getClass().equals(StatePopRequest.class)) {
            states.poll();
        }
        if (request.getClass().equals(StateClearRequest.class)) {
            states.clear();
        }
        if (request.getClass().equals(StatePushRequest.class)) {
            final StatePushRequest pushRequest = (StatePushRequest)request;
            states.offerFirst(pushRequest.getRequestedState());
        }
    }
}
