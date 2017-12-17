@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
        if ((height <=0) || (width <=0))  throw IllegalArgumentException()
        else return MatrixImpl(height, width, e)

}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> : Matrix<E> {
    private val cellsValue: MutableMap<Cell, E> = mutableMapOf()

    override val height: Int

    override val width: Int

    constructor(height: Int, width: Int, e: E) {
        this.height = height
        this.width = width
        for (i in 0 until height) {
            for (j in 0 until width) {
                cellsValue.put(Cell(i, j), e)
            }
        }
    }

    //private val map = mutableMapOf<Cell, E>()


    override fun get(row: Int, column: Int): E = cellsValue[Cell(row, column)]!!

    override fun get(cell: Cell): E  = cellsValue[cell]!!

    fun inside (row: Int, column: Int) : Boolean = !(row !in 0 until height || column !in 0 until width)
    fun inside (cell: Cell) : Boolean = !(cell.row !in 0 until height || cell.column !in 0 until width)

    override fun set(row: Int, column: Int, value: E) = TODO()

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append('[')
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (j > 0) sb.append(", ")
                sb.append(this[i, j])
            }
            sb.append(']')
            if (i > 0) sb.append(", ")
            sb.append('[')
        }
        sb.append(']')
        return "$sb"
    }
}


