import java.awt.*;

/**
 * Created by Kyle on 5/28/2015.
 */
public class SeamCarver
{
    private Picture picture;
    private int width, height;
    private double[][] energy;
    private WeightedDiGraph<String> graph;

    public SeamCarver(Picture picture)
    {
        setPicture(new Picture(picture));
    }

    public Picture picture()
    {
        return picture;
    }

    public int width()
    {
        return width;
    }

    public int height()
    {
        return height;
    }

    public double energy(int x, int y)
    {
        if (x < 0 || x >= width || y < 0 || y >= height)
            throw new IndexOutOfBoundsException();

        return energy[x][y];
    }

    public int[] findHorizontalSeam()
    {
        return new int[0];
    }

    public int[] findVerticalSeam()
    {
        return lazyVerticalSeam();
    }

    public void removeHorizontalSeam(int[] seam)
    {

    }

    public void removeVerticalSeam(int[] seam)
    {
        Picture original = picture;
        setPicture(new Picture(original.width() - 1, original.height()));

        int originalWidth = original.width();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0, xOriginal = 0; x < width && xOriginal < originalWidth; x++, xOriginal++)
            {
                if (x == seam[y]) xOriginal++;
                picture.set(x, y, original.get(xOriginal, y));
            }
        }
    }

    private int[] graphLeastVerticalWeight()
    {

        return new int[0];
    }

    private int[] lazyVerticalSeam()
    {
        int[] seam = new int[height()];

        // get the first column
        double minEnergy = Double.MAX_VALUE;
        for (int x = 0; x < width; x++)
        {
            double energy = energy(x, 0);
            if (energy < minEnergy) {
                minEnergy = energy;
                seam[0] = x;
            }
        }

        // get the rest
        for (int y = 1; y < seam.length; y++)
        {
            int prevCol = seam[y - 1];
            double left = (prevCol > 0) ? energy(prevCol - 1, y) : Double.MAX_VALUE;
            double center = energy(prevCol, y);
            double right = (prevCol < width() - 1) ? energy(prevCol + 1, y) : Double.MAX_VALUE;

            if (left <= center && left <= right) seam[y] = prevCol - 1;
            else if (center <= left && center <= right) seam[y] = prevCol;
            else seam[y] = prevCol + 1;
        }

        return seam;
    }

    public Picture getEnergyPicture()
    {
        Picture energyPicture = new Picture(width, height);

        double[] energyBounds = energyBounds();
        double minEnergy = energyBounds[0];
        double maxEnergy = energyBounds[1];

        // Protect against dividing against zero
        if (minEnergy == maxEnergy) return energyPicture;

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                double energy = energy(x, y);
                int scaledColorValue = (int)((255 * (energy - minEnergy)) / (maxEnergy - minEnergy));

                Color color = new Color(scaledColorValue, scaledColorValue, scaledColorValue);

                energyPicture.set(x, y, color);
            }
        }

        return energyPicture;
    }

    private double[] energyBounds()
    {
        double max = energy(0, 0);
        double min = max;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                double energy = energy(x, y);
                if (energy > max) max = energy;
                else if (energy < min) min = energy;
            }
        }

        return new double[] { min, max };
    }

    private void setPicture(Picture picture)
    {
        this.picture = picture;
        width = picture.width();
        height = picture.height();
        calculateEnergy();
        buildGraph();
    }

    private void calculateEnergy()
    {
        if (energy == null) energy = new double[width][height];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float[] leftPixel   = picture.get((x > 0) ? x - 1 : width() - 1, y).getRGBColorComponents(null);
                float[] rightPixel  = picture.get((x < width() - 1) ? x + 1 : 0, y).getRGBColorComponents(null);
                float[] topPixel    = picture.get(x, (y < height() - 1) ? y + 1 : 0).getRGBColorComponents(null);
                float[] bottomPixel = picture.get(x, (y > 0) ? y - 1 : 0).getRGBColorComponents(null);

                double pixelEnergy = 0.0;

                for (int i = 0; i < 3; i++)
                {
                    pixelEnergy += Math.pow(Math.abs(leftPixel[i] - rightPixel[i]) * 255, 2);
                    pixelEnergy += Math.pow(Math.abs(topPixel[i] - bottomPixel[i]) * 255, 2);
                }

                energy[x][y] = pixelEnergy;
            }
        }
    }

    private void buildGraph()
    {
        graph = new WeightedDiGraph(width * height);

        for (int y = 0; y < height - 1; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int nextRow = y + 1;
                int left = x - 1;
                int right = x + 1;

                String currentId = toId(x, y);
                String straightId = toId(x, nextRow);
                graph.addEdge(currentId, straightId, energy(x, nextRow));

                if (left >= 0)
                {
                    String leftId = toId(left, nextRow);
                    graph.addEdge(currentId, leftId, energy(left, nextRow));
                }
                else if (right <= width - 1)
                {
                    String rightId = toId(right, nextRow);
                    graph.addEdge(currentId, rightId, energy(right, nextRow));
                }
            }
        }
    }

    private String toId(int x, int y)
    {
        return String.valueOf(x) + "," + String.valueOf(y);
    }
}
