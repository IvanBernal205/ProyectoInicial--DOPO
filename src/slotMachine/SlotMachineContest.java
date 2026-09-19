package slotMachine;
import java.util.ArrayList;

/**
 * This class represents a contest for a slot machine. 
 * It provides methods to solve the contest and simulate the slot machine behavior.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 19/09/2026
 */
public class SlotMachineContest
{
    private SlotMachine sm;

    /**
     * Solves the slot machine contest for a given number of wheels.
     * @param n The number of wheels and symbols in the slot machine.
     * @return An array representing the solution.
     */
    public int[][] solve(int n){
        sm = new SlotMachine(n);
        String[] g = sm.configuration();
        ArrayList<int[]> solution = new ArrayList<>();

        for(int i = 1; i<n; i++){
            int ditinctSym = sm.distinctSymbols();
            if (ditinctSym == n) break;
            int cont = 0;
            // Gira la rueda n hasta que halla un simbolo 
            // distinto nuevo.
            while(sm.distinctSymbols() != ditinctSym+1 && cont<n){
                sm.spin(i, 1);
                solution.add(new int[]{i, 1});
                cont++;
            }
        }

        sm.spin(1,1);
        solution.add(new int[]{1, 1});
        ArrayList<Integer> alreadyCheck = new ArrayList<>();
        alreadyCheck.add(1);

        for(int i = 2; i<=n; i++){
            for(int j = 2; j<=n; j++){
                if(sm.distinctSymbols()==1) break; // Si ya se llego al jackpot para.
                if(alreadyCheck.contains(j)) continue; // Si esa rueda ya tiene el simbolo correcto no verifica nada.
                
                int dist = sm.distinctSymbols();
                int move = alreadyCheck.size();

                sm.spin(j, move);
                solution.add(new int[]{j, move});

                if(dist > sm.distinctSymbols()) { // Si el número de simbolos disminuye reversa el movimiento.
                    sm.spin(j, -move);
                    solution.add(new int[]{j, -move});
                    continue;
                }

                sm.spin(j, 1);
                solution.add(new int[]{j, 1});

                if(sm.distinctSymbols() >= dist){ // Si aumenta el numero de simbolos o es igual que antes reversa.
                    sm.spin(j, -move-1);
                    solution.add(new int[]{j, -move-1});
                    continue;
                }

                alreadyCheck.add(j); // Añade la rueda que ya tiene el simbolo final 
                break;
            }
        }
        
        
        return solution.toArray(new int[0][]);
    }
    
    /**
     * Simulates the slot machine contest for a given number of wheels.
     * @param n The number of wheels and symbols in the slot machine.
     */
    public void simulate(int n){
        int[][] solution = solve(n);
        int t = solution.length;

        for(int i = 0; i < solution.length; i++){
            sm.spin(solution[t-i-1][0], -solution[t-i-1][1]);
        }
        sm.makeVisible();
        for(int i = 0; i < solution.length; i++){
            sm.spin(solution[i][0], solution[i][1]);
        }
        sm.isJackpot();
    }

    public int[][] solve2(int n){
        return new int[0][];
    }

    public void simulate2(int n){

    }
}