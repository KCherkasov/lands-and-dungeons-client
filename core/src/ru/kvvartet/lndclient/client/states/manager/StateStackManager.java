package ru.kvvartet.lndclient.client.states.manager;

import com.badlogic.gdx.Game;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.client.states.manager.requests.StateClearRequest;
import ru.kvvartet.lndclient.client.states.manager.requests.StateManagerRequest;
import ru.kvvartet.lndclient.client.states.manager.requests.StatePopRequest;
import ru.kvvartet.lndclient.client.states.manager.requests.StatePushRequest;
import ru.kvvartet.lndclient.client.states.state.GameState;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class StateStackManager implements StateManager {
    private final Game game;

    private final Deque<GameState> states = new ArrayDeque<>();
    private final Queue<StateManagerRequest> pendingRequests = new ArrayDeque<>();

    public StateStackManager(@NotNull Game game) {
        this.game = game;
    }

    @Override
    public void requestStatePop() {
        pendingRequests.offer(new StatePopRequest());
    }

    @Override
    public void requestStateClear() {
        pendingRequests.offer(new StateClearRequest());
    }

    @Override
    public void requestStatePush(@NotNull GameState gameState) {
        gameState.setupStage();
        pendingRequests.offer(new StatePushRequest(gameState));
    }

    @Override
    public void update(float timeDelta) {
        for (GameState state : states) {
            if (state != null) {
                if (game.getScreen() == null
                        || !game.getScreen().equals(state)) {
                    game.setScreen(state);
                    System.out.println(state.getClass().getName());
                }
                state.render(timeDelta);
                if (state.isBlocking()) {
                    break;
                }
            }
        }
        processRequests();
    }

    private void processRequests() {
        while (!pendingRequests.isEmpty()) {
            if (pendingRequests.peek() != null) {
                processRequest(pendingRequests.poll());
            } else {
                pendingRequests.poll();
            }
        }
    }

    private void processRequest(@NotNull StateManagerRequest request) {
        if (request.getClass().equals(StatePopRequest.class)) {
            final GameState popped = states.poll();
            if (popped != null) {
                popped.dispose();
            }
        }
        if (request.getClass().equals(StateClearRequest.class)) {
            for (GameState state : states) {
                if (state != null) {
                    state.dispose();
                }
            }
            states.clear();
        }
        if (request.getClass().equals(StatePushRequest.class)) {
            final StatePushRequest pushRequest = (StatePushRequest)request;
            states.offerFirst(pushRequest.getGameState());
        }
    }
}
