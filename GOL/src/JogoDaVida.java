import java.util.Random;

public class JogoDaVida {
    private int width;
    private int height;
    private boolean[][] grid;
    private int geracaoAtual = 0;

    public JogoDaVida(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[height][width];
        inicializarGrid();
    }

    private void inicializarGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void preencherGridComStringCLI(String input) {
        int index = 0;
        for (int i = 0; i < height && index < input.length(); i++) {
            for (int j = 0; j < width && index < input.length(); j++) {
                grid[i][j] = input.charAt(index) == '1';
                index++;
            }
        }
    }

    public void preencherGradeComValoresAleatorios() {
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = random.nextBoolean();
            }
        }
    }

    public void imprimirGrid() {
        System.out.println("Geração: " + geracaoAtual);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] ? "O " : ". ");
            }
            System.out.println();
        }
        geracaoAtual++;
    }

    public void atualizarGrid() {
        boolean[][] novaGrid = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int vizinhosVivos = contarVizinhosVivos(i, j);
                if (grid[i][j]) {
                    novaGrid[i][j] = vizinhosVivos == 2 || vizinhosVivos == 3;
                } else {
                    novaGrid[i][j] = vizinhosVivos == 3;
                }
            }
        }

        grid = novaGrid;
    }

    private int contarVizinhosVivos(int x, int y) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int novoX = x + i;
                int novoY = y + j;
                if (novoX >= 0 && novoX < height && novoY >= 0 && novoY < width) {
                    if (grid[novoX][novoY]) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    public boolean todasCelulasMortas() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
