package br.com.amumus.model;

public class GaussSeidel {

    public static Resultado calcular(double[][] a, double[] b, double epsilon, int maxIteracoes) {
        int n = b.length;
        double[] xAtual = new double[n];
        double[] xAnterior = new double[n];
        int iteracoesRealizadas = 0;
        double erroRelativo = 1.0;

        for (int k = 0; k < maxIteracoes; k++) {
            System.arraycopy(xAtual, 0, xAnterior, 0, n);

            for (int i = 0; i < n; i++) {
                double soma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {

                        soma += a[i][j] * xAtual[j];
                    }
                }
                xAtual[i] = (b[i] - soma) / a[i][i];
            }

            iteracoesRealizadas++;

            double maxDiff = 0;
            double maxX = 0;

            for (int i = 0; i < n; i++) {
                double diff = Math.abs(xAtual[i] - xAnterior[i]);
                if (diff > maxDiff) maxDiff = diff;
                if (Math.abs(xAtual[i]) > maxX) maxX = Math.abs(xAtual[i]);
            }

            erroRelativo = maxDiff / maxX;

            if (erroRelativo <= epsilon) {
                break;
            }
        }

        return new Resultado(xAtual, erroRelativo, iteracoesRealizadas);
    }

    public static class Resultado {
        public double[] valores;
        public double erro;
        public int iteracoes;

        public Resultado(double[] valores, double erro, int iteracoes) {
            this.valores = valores;
            this.erro = erro;
            this.iteracoes = iteracoes;
        }
    }
}