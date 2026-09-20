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
    SlotMachine stm;
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
        SlotMachine sm = new SlotMachine (n);
        //sm.makeVisible();
        stm = sm;
        
        int k;
        int steps = 0;
        ArrayList <int []> solution = new ArrayList<>();
        
        //Parte 1 Disminuir k hasta 2
        
        k = sm.distinctSymbols();
        while (k > 2){ //Se repite hasta que k baje a 2
            
            for (int i = 1; i <= n ; i++){ //girar las n ruedas
                steps = 0;
                
                while(steps < n && sm.distinctSymbols() >= k ){//girar 1 rueda n veces o hasta que disminuya k
                    sm.spin(i, 1);
                    steps++;
                    if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]);
                }
                if (steps < n) solution.add(new int[]{i,steps});
                if (sm.distinctSymbols() < k)break;//si disminuye k, el proceso se vuelve a hacer desde el comienzo
            }
            
            if (sm.distinctSymbols() < k){
                k = sm.distinctSymbols();
                continue;
            }
            
            if(sm.distinctSymbols() == k){//no hubo la posibilidad de disminuir k
                int kBefore = k;
                int positionDif = 1;
                steps = 0;
                
                for (int j = 1; j <= n ; j++){//se debe aumentar k para realizar verificaciones
                    sm.spin(positionDif,1); 
                    if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]);
                    
                    if (sm.distinctSymbols() > kBefore){
                        steps = j;
                        kBefore = sm.distinctSymbols();
                        break;
                    }
                }
                solution.add(new int[]{positionDif, steps});
                
                if (positionDif != 0){
                    for (int i = 1; i <= n; i++){
                        if (i == positionDif) continue;
                        
                        sm.spin(i, steps);
                        if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]);
                        
                        if(sm.distinctSymbols() == kBefore){ 
                            solution.add(new int[]{i,steps});
                            //Son la misma posicion
                        }
                        else if(sm.distinctSymbols() < kBefore){ //disminuye
                            k = sm.distinctSymbols();
                            solution.add(new int[]{i,steps});
                            break;
                        }
                        else if(sm.distinctSymbols() > kBefore){ //eran diferentes
                            sm.spin(i, -steps);
                            break;
                        }
                    }
                }   
            }
            k = sm.distinctSymbols();
        }
        
        //Parte 2 k=2 Hallar iguales
        int distance = 0;
        for (int i = 1; i <= n; i++){ //Hallar los pasos para simbolo que no esta
            sm.spin(1, 1);
            steps = i;
            if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]);
            if (sm.distinctSymbols() == 2){
                distance = i;
                break;
            }
        }
        
        sm.spin(1, -distance);
        int distanceBetween = 0;
        
        for (int possible = 1; possible < n ; possible++){
            if(possible != distance && possible != n-distance){
                distanceBetween = possible;
                break;
            }
        }
        
        if (distanceBetween == 0) {
            distanceBetween = 1;
        }
        
        sm.spin(1, distanceBetween);
        if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]);
        
        steps = distanceBetween;
        solution.add(new int[]{1, steps});
        
        int kBefore = sm.distinctSymbols();
        ArrayList<Integer> sameSymbols = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++){
            sm.spin(i, steps);
            if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]) ;
            if (sm.distinctSymbols() == kBefore){ //son iguales
                sameSymbols.add(i);
                solution.add(new int[]{i, steps});
                sm.spin(i, -steps);
            }
            else{ //son diferentes
                sm.spin(i, -steps);
                if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]) ;
            }
        }
        //Ya se identificaron simbolos iguales
        sm.spin(1, -steps); 
        if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]);
        
        //Parte 3 
        for (int i = 1; i <= n; i++){
            sm.spin(1,1);
            steps = i;
            if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]) ;
            if (sm.distinctSymbols() == 2) break;
        }//se hallo la distancia entre los simbolos restantes
        solution.add(new int[]{1,steps});
        
        for (int wheel : sameSymbols){
            sm.spin(wheel, steps);
            if (sm.distinctSymbols() == 1)return solution.toArray(new int[0][]) ;
            solution.add(new int[]{wheel,steps}); 
        }
        
        return solution.toArray(new int[0][]);
    }
 
    public void simulate2(int n){
        int[][] solution = solve2(n);
        if (solution == null) return;
        int t = solution.length; 
        
        for (int i = 0; i < t; i++) {
            stm.spin(solution[t - i - 1][0], -solution[t - i - 1][1]);
        }
    
        stm.makeVisible();
        
        for (int i = 0; i < t; i++) {
            stm.spin(solution[i][0], solution[i][1]);
        }
    
        stm.isJackpot();
    }
}