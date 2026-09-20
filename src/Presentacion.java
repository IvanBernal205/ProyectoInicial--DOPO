import slotMachine.SlotMachineContest;

/**
* Casos de prueba pra la presentacion.
*/
public class Presentacion
{
        public static void main(String[] args) {
                SlotMachineContest smc = new SlotMachineContest();
                smc.simulate(7);

                SlotMachineContest smc2 = new SlotMachineContest();
                smc2.simulate(22);
        }
}