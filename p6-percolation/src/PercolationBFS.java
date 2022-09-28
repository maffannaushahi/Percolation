import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

    public PercolationBFS (int n){
        super(n);

    }

    @Override
    protected void search(int row, int col) {
        if (isFull(row, col) || !isOpen(row, col))
            return;

        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        Queue<int[]> q = new LinkedList<>();
        myGrid[row][col] = FULL;
        q.add(new int[]{row, col});
        int r;
        int c;

        while(q.size() != 0){

            int p[] = q.remove();

            for(int i = 0; i < rowDelta.length; i++){

                r = p[0] + rowDelta[i];
                c = p[1] + colDelta[i];
                if (inBounds(r,c) && isOpen(r, c) && !isFull(r, c)){

                    q.add(new int[]{r, c});
                    myGrid[r][c] = FULL;
                }
            }
        } 

    }
    
    
}
