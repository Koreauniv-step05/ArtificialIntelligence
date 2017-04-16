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
//        System.out.println("alphabetaSearchForFindBestState");
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
//                System.out.println("Maximizer depth : " + depth);
                float w = alphabeta((Tree) child, depth + 1, alpha, beta, false, maximumDepth);
                if (v < w) {
//                    System.out.println("update v");
                    bestState = (State)((Tree) child).getData();
                    v = w;
                }
//                alpha = print_max(alpha, v, "alpha");
                alpha = max(alpha, v);
                if (beta <= alpha) {
//                    System.out.println("beta cutoff alpha : " + alpha + " beta : " + beta);
                    break;
                } else {
//                    System.out.println("No beta cutoff alpha : " + alpha + " beta : " + beta);
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
//                System.out.println("Maximizer depth : " +  depth);
//                v = print_max(v, alphabeta((Tree)child, depth + 1, alpha, beta, false, maximumDepth),"v");
                v = max(v, alphabeta((Tree)child, depth + 1, alpha, beta, false, maximumDepth));
//                alpha = print_max(alpha, v,"alpha");
                alpha = max(alpha, v);
                if(beta <= alpha) {
//                    System.out.println("beta cutoff alpha : " +  alpha + " beta : " + beta);
                    break;
                } else {
//                    System.out.println("No beta cutoff alpha : " +  alpha + " beta : " + beta);
                }
            }

            return v;
        } else {
            v = INF;
            for (Object child:
                 node.getChildren()) {
//                System.out.println("Minimizer depth : " +  depth);
//                v = -print_max(-v, -alphabeta((Tree)child, depth + 1, alpha, beta, true, maximumDepth), "v");
                v = -max(-v, -alphabeta((Tree)child, depth + 1, alpha, beta, true, maximumDepth));
//                System.out.println("beta");
//                beta = -print_max(-beta, -v, "beta");
                beta = -max(-beta, -v);
                if(beta <= alpha) {
//                    System.out.println("alpha cutoff alpha : " +  alpha + " beta : " + beta);
                    break;
                } else {
//                    System.out.println("No alpha cutoff alpha : " +  alpha + " beta : " + beta);
                }
            }
            return v;
        }
    }

    private static float print_max(float prevone, float newone, String type) {
        System.out.println(type);
        return max(prevone, newone);
    }

    private static float max(float prevone, float newone) {
        if(prevone > newone) {
            return prevone;
        } else {
//            System.out.println("update to "+newone);
            return newone;
        }
    }
}
