package ai.algorithm;

import ai.domain.State;
import ai.utils.GomokuTree;
import ai.utils.Tree;

import static consts.Consts.GAMEEND;
import static consts.Consts.INF;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class Alphabeta {
    private static boolean terminateFlag = false;

    public static void onTimeLimited() {
        terminateFlag = true;
    }

    public static State alphabetaSearchForFindBestState(Tree node, int depth,
                                                        float alpha, float beta, boolean maximizingPlayer, int maximumDepth) {
        float v = 0;
        State bestState = (State)node.getData();

        float evaluation = node.eval();
        if(evaluation >= GAMEEND || evaluation <= (-GAMEEND) || node.numOfChildren() == 0 || depth == maximumDepth) {
            return bestState;
        }

        if (maximizingPlayer) {
            v = -INF;
            for (Object child :
                    node.getChildren()) {
                float w = alphabeta((Tree) child, depth + 1, alpha, beta, false, maximumDepth);
                if (v < w) {
                    bestState = (State)((Tree) child).getData();
                    v = w;
                }
                alpha = max(alpha, v);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        return bestState;
    }

    public static float alphabeta(Tree node, int depth,
                                  float alpha, float beta, boolean maximizingPlayer, int maximumDepth) {
        float v = 0;

        float evaluation = node.eval();
        if(evaluation >= GAMEEND || evaluation <= (-GAMEEND) || node.numOfChildren() == 0 || depth == maximumDepth || terminateFlag) {
            return evaluation;
        }

        if (maximizingPlayer) {
            v = -INF;
            for (Object child:
                 node.getChildren()) {
                v = max(v, alphabeta((Tree)child, depth + 1, alpha, beta, false, maximumDepth));
                alpha = max(alpha, v);
                if(beta <= alpha) {
                    break;
                }
            }

            return v;
        } else {
            v = INF;
            for (Object child:
                 node.getChildren()) {
                v = -max(-v, -alphabeta((Tree)child, depth + 1, alpha, beta, true, maximumDepth));
                beta = -max(-beta, -v);
                if(beta <= alpha) {
                    break;
                }
            }
            return v;
        }
    }

    private static float max(float prevone, float newone) {
        if(prevone > newone) {
            return prevone;
        } else {
            return newone;
        }
    }
}
