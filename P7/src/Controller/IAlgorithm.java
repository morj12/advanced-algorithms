package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public interface IAlgorithm {
    public abstract void identifyFlag(Flag flag, int nPuntos, boolean singleThread);
    public boolean similarColorimetry(Colorimetry candidate, Colorimetry target);
    public void contrastColorimetryAgainstDatabase(Colorimetry foundColorimetry);
    public Colorimetry launchThreads(ExecutorService threadPool, Palette paleta, int nThreads, ArrayList<Callable<Colorimetry>> tasks);
}
