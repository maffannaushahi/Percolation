public class PercolationUF implements IPercolate{

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size){
        myFinder = finder;
        myFinder.initialize(size*size + 2);
        myGrid = new boolean[size][size];
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }

    @Override
    public void open(int row, int col) {
        if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length ) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        if(!isOpen(row, col)){
            myGrid[row][col] = true;
            myOpenCount++;
            if(row == 0){
                myFinder.union(row*myGrid.length + col, VTOP);
            }
            else{
                if(isOpen(row-1, col)) {
                    myFinder.union(row * myGrid.length + col, (row - 1) * myGrid.length + col);
                }
            }
            if(row == myGrid.length - 1){
                myFinder.union(row*myGrid.length + col, VBOTTOM);
            }
            else{
                if(isOpen(row+1, col)) {
                    myFinder.union(row * myGrid.length + col, (row + 1) * myGrid.length + col);
                }
            }
            if(col > 0){
                if(isOpen(row, col - 1)) {
                    myFinder.union(row * myGrid.length + col, row * myGrid.length + col - 1);
                }
            }
            if(col < myGrid.length - 1){
                if(isOpen(row, col + 1)) {
                    myFinder.union(row * myGrid.length + col, row * myGrid.length + col + 1);
                }
            }
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length ) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        else return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= myGrid.length || col < 0 || col >= myGrid.length ) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myFinder.connected(row*myGrid.length + col, VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
