public class Main {
    public static void main(String[] args) {
        int width = 3;
        int height = 3;
        int generations = 100; // Valor padrão (-1 para indefinido)
        int speed = 300;
        String population = null;
        int vizinhanca = 3;

        for (String arg : args) {
            if (arg.startsWith("w=")) {
                try {
                    width = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("width = Invalido");
                    return;
                }
                System.out.println("width = [" + width + "]");
            } else if (arg.startsWith("h=")) {
                try {
                    height = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("height = Invalido");
                    return;
                }
                System.out.println("height = [" + height + "]");
            } else if (arg.startsWith("g=")) {
                try {
                    generations = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("generations = Invalido");
                    return;
                }
                System.out.println("generations = [" + generations + "]");
            } else if (arg.startsWith("s=")) {
                try {
                    speed = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("speed = Invalido");
                    return;
                }
                System.out.println("speed = [" + speed + "]");


            } else if (arg.startsWith("p=")) {
                population = arg.substring(2).replace("#", "\n");
                System.out.println("population = [" + population + "]");



            } else if (arg.startsWith("n=")) {
                try {
                    vizinhanca = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("vizinhança = Invalido");
                    return;
                }
                System.out.println("vizinhança inicial = [" + vizinhanca + "]");
            }
        }

        JogoDaVida jogo = new JogoDaVida(width, height);

        if (population != null) {
            jogo.preencherGridComStringCLI(population);
        } else {
            jogo.preencherGradeComValoresAleatorios();
        }

        jogo.imprimirGrid();

        if (generations == -1) {
            loopIndefinido(jogo, speed);
        } else {
            loopAtravesDeTodasGeracoes(jogo, generations, speed);
        }

        System.out.println("Simulação concluída.");
    }



    public static boolean funcaolidarPopulation(String population){
        if (population == null || population.isEmpty()){
            return false;
        }else{
            return true;
        }
    };

    //teste
    private static void loopAtravesDeTodasGeracoes(JogoDaVida jogo, int numGeracoes, int speed) {
        for (int i = 0; i < numGeracoes; i++) {
            if (jogo.todasCelulasMortas()) {
                break;
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            jogo.atualizarGrid();
            jogo.imprimirGrid();
        }
    }


    private static void loopIndefinido(JogoDaVida jogo, int speed) {
        while (!jogo.todasCelulasMortas()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            jogo.atualizarGrid();
            jogo.imprimirGrid();
        }
    }
}
