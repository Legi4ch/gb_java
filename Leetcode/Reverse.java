package Leetcode;

/**
 * На вход подается размер двумерного квадратного массива
 * выводит заполенный инкрементом массив по спирали против часовой стрелки
 */

class Reverse {
    private int[][] matrix;
    private int cols = 0;
    private int rows = 0;
    private int levels = 0;

    public Reverse(int _rows,int _cols) throws MatrixInitErrorException {
        if ((_cols > 0 || _rows > 0) && _rows == _cols) {
            this.matrix = new int[_rows][_cols];
            this.rows = _rows;
            this.cols = _cols;
            this.levels = getLayersCount(_rows);
            fillByIncrement(_rows,_cols);
        } else {
            throw new MatrixInitErrorException("Both of cols and rows should be above zero and should be square!");
        }
    }

    //заполняем инкрементом по дефолту
    private void fillByIncrement(int _rows,int _cols) {
        int k = 0;
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _cols; j++) {
                this.matrix[i][j] = k++;
            }
        }
    }

    @Override
    public String toString() {
        String result = new String();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result += (matrix[i][j] / 10 == 0) ? " " + matrix[i][j] + " " : matrix[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }


    //обход всей матрицы против часовой стрелки на всех уровнях
    public void fillCCW() {
        int len = 0;
        for (int i = 0; i < this.levels; i++) {
            len += getLayerPerimetr(i - 1);
            ccvLayerFill(i,len);
        }
    }


    /**
     * обходим внешний слой двумерного массива против часовой стрелки
     * и помешаем внутрь инкремент
     */
    private void ccvLayerFill(int layer,int put) {

        //первый левый столбец
        for (int i = layer; i < this.rows - layer; i++) {
            this.matrix[i][layer] = put++;
        }
        //поворот на 90° и нижняя строка / угловой элемент пропускаем
        for (int i = layer + 1; i < this.cols - layer; i++) {
            this.matrix[this.rows - 1 - layer][i] = put++;
        }
        //поворот на 90° и правый столбик / угол снова пропускаем
        for (int i = this.rows - 2 - layer; i >= layer; i--) {
            this.matrix[i][this.cols - 1 - layer] = put++;
        }
        //поворот на 90° верхняя строка / пропускаем уже два верхних угла, левый и правый
        for (int i = this.cols - 2 - layer; i > layer; i--) {
            this.matrix[layer][i] = put++;
        }

    }

    // возвращает периметр нужного слоя массива
    private int getLayerPerimetr(int layer) {
        if (layer < 0) {
            return 0;
        }
        int p = 0;
        for (int i = layer; i < this.cols - 1 - layer; i++) {
            p++;
        }
        return (p * 4);
    }


    // Для возврата уровня вложенности матрицы
    private int getLayersCount(int _rows) {
        if (_rows % 2 == 0) {
            return (_rows / 2);
        } else {
            return (_rows / 2) + 1;
        }
    }


}
