package OnLatticeExample;

import HAL.GridsAndAgents.AgentGrid2D;
import HAL.GridsAndAgents.AgentSQ2Dunstackable;
import HAL.Gui.GridWindow;
import HAL.Rand;
import HAL.Tools.FileIO;
import HAL.Util;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.io.FileWriter;

class ExampleCell extends AgentSQ2Dunstackable<ExampleGrid> {
    int typeCell;
    double dist;


    public void Init(int typeCell,  double dist){
        this.typeCell = typeCell;
        this.dist = dist;
    }

    ///////////////////////////////////////////////////////
    /////// start new stepCell routine
    public void StepCelltest() { // it can do all 3 in 1 step?
        Random rd = new Random();
        double myRand = rd.nextDouble(); // G.rng;
        if ((this.typeCell != 2) && (this != null)) { // for tumor cells
            this.typeCell = 1;
            /// start for tumor cells within the sheltering radius for a had cut-off radius
            if ((this.dist <= G.distt) == true) {
                if ((myRand >= G.divProb) && (myRand < (G.dieProb + G.divProb))) {
                    this.typeCell = 1;
                    Dispose();
                    ++G.numDie;
                } else if ((myRand < G.divProb) == true) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        ++G.numDiv;
                        this.typeCell = 0;
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    }
                } else if ((myRand > (G.divProb + G.dieProb) == true)& (myRand < (G.divProb + G.dieProb + G.migr) == true)) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    } Dispose();
                }
            }
            /////////////// end within sheltering radius
            /// start for tumor cells outside the sheltering radius for a had cut-off radius
            if ((this.dist > G.distt) == true) {
                if ((myRand >= G.divProbR) && (myRand < (G.dieProbR + G.divProbR))) {
                    Dispose();
                    ++G.numDie;
                } else if ((myRand < G.divProbR) == true) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        ++G.numDiv;
                        this.typeCell = 0;
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    }
                } else if ((myRand > (G.divProbR + G.dieProbR) == true)& (myRand < (G.divProb + G.dieProb + G.migr) == true)) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    } Dispose();
                }
            }
            /////////////// end outside sheltering radius
        } //// end for tumor cells
        else if (this.typeCell == 2) { /// start stroma cells

            if (myRand < G.tmpStr*0.00) {
                Dispose();
            }
            else if ((myRand < G.tmpStr*0.00)) {
                int options = MapEmptyHood(G.divHood);
                if (options > 0) {
                    ++G.numStr;
                    int ind = G.rng.Int(options);
                    G.NewAgentSQ(G.divHood[ind]).Init(2, 0);
                } }
        } /// end stroma cells
    }
    /////////////////////////////////////////////////////////
    //////////////////the stepCell under additional treatment/////
    public void StepCelltest2() { // it can do all 3 in 1 step?
        Random rd = new Random();
        double myRand = rd.nextDouble(); // G.rng;
        if ((this.typeCell != 2) && (this != null)) { // for tumor cells
            this.typeCell = 1;
            /// start for tumor cells within the sheltering radius for a had cut-off radius
            if ((this.dist <= G.distt2) == true) {
                //System.out.println(this.dist);
                if ((myRand >= G.divProb2) && (myRand < (G.dieProb2 + G.divProb2))) {
                    Dispose();
                    ++G.numDie;
                } else if ((myRand < G.divProb2) == true) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        ++G.numDiv;
                        this.typeCell = 0;
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    }
                } else if ((myRand >= (G.divProb2 + G.dieProb2) == true) & (myRand < (G.divProb2 + G.dieProb2 + G.migr) == true)) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    } Dispose();
                }
            }
            /////////////// end within sheltering radius
            /// start for tumor cells outside the sheltering radius for a had cut-off radius
            if ((this.dist > G.distt2) == true) {
                if ((myRand >= G.divProbR2) && (myRand < (G.dieProbR2 + G.divProbR2))) {
                    Dispose();
                    ++G.numDie;
                } else if ((myRand < G.divProbR2) == true) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        ++G.numDiv;
                        this.typeCell = 0;
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    }
                } else if ((myRand >= (G.divProbR2 + G.dieProbR2) == true) & (myRand < (G.divProb2 + G.dieProb2 + G.migr) == true)) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStr[G.divHood[ind]]);
                    }  Dispose();
                }
            }
            /////////////// end outside sheltering radius
        } //// end for tumor cells
        else if (this.typeCell == 2) { /// start stroma cells

            if (myRand < G.tmpStr*0.00) {
                Dispose();
            }
            else if ((myRand < G.tmpStr*0.00)) {
                int options = MapEmptyHood(G.divHood);
                if (options > 0) {
                    ++G.numStr;
                    int ind = G.rng.Int(options);
                    G.NewAgentSQ(G.divHood[ind]).Init(2, 0);
                } }
        } /// end stroma cells
    }
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////
    //////////////////the stepCell under additional treatment/////
    public void StepCellEdge() { // it can do all 3 in 1 step?
        Random rd = new Random();
        double myRand = rd.nextDouble(); // G.rng;
        if ((this.typeCell != 2) && (this != null)) { // for tumor cells
            this.typeCell = 1;
            /// start for tumor cells within the sheltering radius for a had cut-off radius
            if ((this.dist <= G.distt2) == true) {
                if ((myRand >= G.divProb2) && (myRand < (G.dieProb2 + G.divProb2))) {
                    Dispose();
                    ++G.numDie;
                } else if ((myRand < G.divProb2) == true) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        ++G.numDiv;
                        this.typeCell = 0;
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStrEdge[G.divHood[ind]]);
                    }
                } else if ((myRand >= (G.divProb2 + G.dieProb2) == true) &(myRand < (G.divProb2 + G.dieProb2 + G.migr) == true)) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStrEdge[G.divHood[ind]]);
                    } Dispose();
                }
            }
            /////////////// end within sheltering radius
            /// start for tumor cells outside the sheltering radius for a had cut-off radius
            if ((this.dist > G.distt2) == true) {
                if ((myRand >= G.divProbR2) && (myRand < (G.dieProbR2 + G.divProbR2))) {
                    Dispose();
                    ++G.numDie;
                } else if ((myRand < G.divProbR2) == true) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        ++G.numDiv;
                        this.typeCell = 0;
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStrEdge[G.divHood[ind]]);
                    }
                } else if ((myRand >= (G.divProbR2 + G.dieProbR2) == true) & (myRand < (G.divProb2 + G.dieProb2 + G.migr) == true)) {
                    this.typeCell = 1;
                    int options = MapEmptyHood(G.divHood);
                    if (options > 0) {
                        int ind = G.rng.Int(options);
                        G.NewAgentSQ(G.divHood[ind]).Init(1, G.distStrEdge[G.divHood[ind]]);
                    }  Dispose();
                }
            }
            /////////////// end outside sheltering radius
        } //// end for tumor cells
        else if (this.typeCell == 2) { /// start stroma cells

            if (myRand < G.tmpStr*0.00) {
                Dispose();
            }
            else if ((myRand < G.tmpStr*0.00)) {
                int options = MapEmptyHood(G.divHood);
                if (options > 0) {
                    ++G.numStr;
                    int ind = G.rng.Int(options);
                    G.NewAgentSQ(G.divHood[ind]).Init(2, 0);
                } }
        } /// end stroma cells
    }
    /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
}

public class ExampleGrid extends AgentGrid2D<ExampleCell> {
    public  int numDiv = 0;
    public  int numDie = 0;
    public  int numStr = 0;
    public  int tmpStr = 0;
    public double divProb;   // within radius no additional treatment, just TKI
    public double dieProb;  // within radius no additional treatment, just TKI
    public double divProbR; //outside radius no additional treatment, just TKI
    public double dieProbR; //outside radius no additional treatment, just TKI
    public double divProb2; // within radius + additional treatment
    public double dieProb2; // within radius + additional treatment
    public double divProbR2; // outside radius + additional treatment
    public double dieProbR2;   // outside radius + additional treatment
    public double distt; // sheltering radius distance
    public double distt2; // sheltering radius distance after stroma disruption

    public double migr = 0.05; // migration rate
    public double distStr[];

    public double distStrEdge[];
    public int num;

    Rand rng = new Rand();
    int[] divHood = Util.VonNeumannHood(false);

    public ExampleGrid(int x, int y) throws IOException {
        super(x, y, ExampleCell.class);
        distStr = new double [length];
        String myFile = ReadMyFile("allDist.txt");
        String[] values4 = myFile.split(", ");
        for(int i = 0; i < length ; i ++){distStr[i] = Double.valueOf(values4[i]);}

        distStrEdge = new double [length];
        String myFileEdge = ReadMyFile("allDist.txt");
        String[] valuesEdge = myFileEdge.split(", ");
        for(int i = 0; i < length ; i ++){distStrEdge[i] = Double.valueOf(valuesEdge[i]);}
    }

    public void StepCellR() {
        numDiv = 0;
        numDie = 0;
        for (ExampleCell cell : this) {
            cell.StepCelltest();
            }
        tmpStr = numDie - numDiv;
    }

    public void StepCellR2() {
        numDiv = 0;
        numDie = 0;
        for (ExampleCell cell : this) {
            cell.StepCelltest2();
        }
        tmpStr = numDie - numDiv;
    }

    public void StepCellEdge() {
        numDiv = 0;
        numDie = 0;
        for (ExampleCell cell : this) {
            cell.StepCellEdge();
        }
        tmpStr = numDie - numDiv;
    }

    public ArrayList MyCounter(ArrayList<Integer> myCount){
        myCount.add(numDiv);
        myCount.add(numDie);
        myCount.add(this.Pop());
        return (myCount);
    }


    public ArrayList MyCoords(ArrayList<Integer> myCoord) {
        for (ExampleCell cell : this) {
            myCoord.add(cell.typeCell);
            myCoord.add(cell.Xsq());
            myCoord.add(cell.Xsq());
        }
        return (myCoord);
    }

    public String ReadMyFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public void DrawModel(GridWindow win) {
        for (int i = 0; i < length; i++) {
            int shade = Util.WHITE;
            ExampleCell cell = GetAgent(i);
            if (GetAgent(i) != null) {
                if (cell.typeCell == 0)
                {shade = Util.RED;}
                else if (cell.typeCell == 1)
                {shade = Util.CYAN;}
                else if (cell.typeCell == 2){
                    shade = Util.BLUE;}}
           if (GetAgent(i) != null) {if (cell.dist == 160.0){
                    shade = Util.BLACK;}}
            //if (GetAgent(i) != null) {if (cell.dist == 0.0){
              //  shade = Util.BLUE;}}
            //if (GetAgent(i) != null) {if (cell.dist > 0.01 && cell.dist < 3.1 ){
               // shade = Util.GREEN;}}
            //if (GetAgent(i) != null) {if (cell.dist > 3.01 && cell.dist < 160.0 ){
              //  shade = Util.CYAN;}}

            win.SetPix(i, shade);
        }
    }


    public static void main(String[]args) throws IOException {
        int x = 67;
        int y = 67;
        int timeStep = 1501;


        String StringPath = Util.PWD();
        //GridWindow win = new GridWindow(x,y,8); // 6 is the scaling factor from the grid to the computer screen pixel
        ExampleGrid model = new ExampleGrid(x,y);

        // initialize the system
        String fileName = "QuadratStrOn.txt"; // file with coordinates for stroma
        String myFile = model.ReadMyFile(fileName);
        String[] values = myFile.split(", ");


        //String fileName2 = "QuadratStrOffEdge.txt"; // file with coordinates for stroma
        //String myFile2 = model.ReadMyFile(fileName2);
        //String[] values2 = myFile2.split(", ");

        String myNum = model.ReadMyFile("NumRun.txt");
        String[] Numb = myNum.split(",");
        model.num = Integer.parseInt(Numb[0].trim()); //Integer.valueOf(Numb[0]);

        List<ArrayList> data = new ArrayList<ArrayList>();
        List<ArrayList> CoordDataAfter = new ArrayList<ArrayList>();
        List<ArrayList> CoordDataBefore = new ArrayList<ArrayList>();

        String myParamFile = model.ReadMyFile("ABMparamsBefore" + model.num + ".txt");
        String[] paramValues = myParamFile.split(",");
        String myParamFile2 = model.ReadMyFile("ABMparamsAfter" + model.num + ".txt");
        String[] paramValues2 = myParamFile2.split(",");
        double [] myParams = new double [10];
        for(int i = 0; i < 5 ; i ++){myParams[i] = Double.valueOf(paramValues[i]);}
        for(int i = 0; i < 5 ; i ++){myParams[i+5] = Double.valueOf(paramValues2[i]);}
        model.divProb = myParams[0];
        model.dieProb = myParams[1];
        model.divProbR = myParams[2];
        model.dieProbR = myParams[3];
        model.distt = myParams[4];
        model.divProb2 = myParams[5];
        model.dieProb2 = myParams[6];
        model.divProbR2 = myParams[7];
        model.dieProbR2 = myParams[8];
        model.distt2 = myParams[9];

// Initiate the populations
        for (int i = 0; i < values.length-1; i =i +2) {
            int dnum = Integer.valueOf(values[i]);//Double.parseDouble(values[i]);
            int dnum2 = Integer.valueOf(values[i+1]);
            model.NewAgentSQ(dnum, dnum2).Init(2,  Double.valueOf(model.distStr[dnum*67 + dnum2]));
        }

        //for (int i = 0; i < values2.length-1; i =i +2) {
       //     int dnum = Integer.valueOf(values2[i]);//Double.parseDouble(values[i]);
        //    int dnum2 = Integer.valueOf(values2[i+1]);
       //     model.NewAgentSQ(dnum, dnum2).Init(2,  Double.valueOf(model.distStr[dnum*67 + dnum2]));
     //   }

        for (int i = 0; i < 67; i =i +1) {
            for (int j = 0; j < 67; j =j +1) {
            int dnum = i;
            int dnum2 = j;
            if (model.GetAgent(i,j) == null){
                    model.NewAgentSQ(dnum, dnum2).Init(1,  Double.valueOf(model.distStr[dnum*67 + dnum2]));
        }}}

        for(int i = 0; i < timeStep; i ++){
            ArrayList<Integer> tempCount = new ArrayList<Integer>();
            data.add(model.MyCounter(tempCount));
            if(i <= 350){
            model.IncTick();
            //win.TickPause(100);
            model.StepCellR();

                if (i == 349){
                    ArrayList<Integer> tempCoordsB = new ArrayList<Integer>();
                    CoordDataBefore.add(model.MyCoords(tempCoordsB));
                }
            //model.DrawModel(win);
                }

            else if(i > 350){
                model.IncTick();
               // win.TickPause(100);
                model.StepCellR2(); // apply treatment on top of the TKI

                if (i == 1499){
                    ArrayList<Integer> tempCoords = new ArrayList<Integer>();
                    CoordDataAfter.add(model.MyCoords(tempCoords));
                }
                //model.DrawModel(win);
                }
           if (i %1 ==0){
          //win.ToPNG(StringPath+"TestRunStr"+i+".png");
               }
        }

        FileWriter writer = new FileWriter("ContDeathStr"+ model.num +".txt");
        writer.write(data.toString());
        writer.close();
        FileWriter writerCoordB = new FileWriter("CoordinatesBeforeStr" + model.num +".txt");
        writerCoordB.write(CoordDataBefore.toString());
        writerCoordB.close();
        FileWriter writerCoordA = new FileWriter("CoordinatesAfterStr" + model.num +".txt");
        writerCoordA.write(CoordDataAfter.toString());
        writerCoordA.close();
       // win.Close();

    }
}