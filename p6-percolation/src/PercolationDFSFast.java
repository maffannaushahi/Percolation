public class PercolationDFSFast extends PercolationDFS{
   
    public PercolationDFSFast(int n){
        super(n); 
    }

    @Override 
    protected void updateOnOpen(int row , int col){
        if(row == 0 || (col != 0 && isFull(row, col - 1)) 
        || (col != myGrid[0].length - 1 && isFull(row, col + 1)) 
        || isFull(row - 1, col)
        ||(row != myGrid.length - 1 && isFull(row + 1, col))){
            search(row, col);
        }
    }
}
