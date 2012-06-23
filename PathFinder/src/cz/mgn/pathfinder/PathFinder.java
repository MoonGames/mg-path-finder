/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.pathfinder;

import java.util.ArrayList;

/**
 * Path finder based on A* algorithm
 *
 * @author indy
 */
public class PathFinder {

    /**
     * map width
     */
    protected int width = 0;
    /**
     * map height
     */
    protected int height = 0;
    /**
     * map interface (where search)
     */
    protected PFMapInterface map = null;
    /**
     * edges to previous till
     */
    protected PFPoint[][] paths = null;
    /**
     * actual prices of tills
     */
    protected float[][] prices = null;
    /**
     * tills waiting for expand
     */
    protected ArrayList<PFPoint> toExpand = new ArrayList<PFPoint>();

    /**
     * initeze PathFinder
     *
     * @param map map interface
     * @param mapWidth map width
     * @param mapHeight map height
     */
    public PathFinder(PFMapInterface map, int mapWidth, int mapHeight) {
        this.map = map;
        resetMapSize(mapWidth, mapHeight);
    }

    /**
     * reset pathf finder (need to call befere every new path search)
     */
    protected void resetPathFinder() {
        paths = new PFPoint[width][height];
        prices = new float[width][height];
        for(int x = 0; x < prices.length; x++) {
            for(int y = 0; y < prices[0].length; y++) {
                prices[x][y] = -1;
            }
        }
        toExpand.clear();
    }

    /**
     * reset path finder to new map size
     *
     * @param mapWidth map width
     * @param mapHeight map height
     */
    public void resetMapSize(int mapWidth, int mapHeight) {
        this.width = mapWidth;
        this.height = mapHeight;
    }

    /**
     *
     * @param referential
     * @param newThread
     * @param xStart
     * @param yStart
     * @param xEnd
     * @param yEnd
     * @return best path or null if not exist
     */
    public PFPath findPath(Object referential, boolean newThread, int xStart, int yStart, int xEnd, int yEnd) {
        resetPathFinder();
        toExpand.add(new PFPoint(xStart, yStart));
        prices[xStart][yStart] = 0f;

        while (!toExpand.isEmpty()) {
            PFPoint bestPoint = toExpand.get(0);
            if (bestPoint.x == xEnd && bestPoint.y == yEnd) {
                return reconstructPath(referential, xStart, yStart, xEnd, yEnd);
            }
            toExpand.remove(0);
            expand(referential, bestPoint);
        }
        return new PFPath(null, referential);
    }

    protected PFPath reconstructPath(Object referential, int xStart, int yStart, int xEnd, int yEnd) {
        ArrayList<PFPoint> points = new ArrayList<PFPoint>();
        PFPoint last = new PFPoint(xEnd, yEnd);
        while (last.x != xStart || last.y != yStart) {
            points.add(last);
            last = paths[last.x][last.y];
        }
        points.add(last);
        return new PFPath(points, referential);
    }

    protected void expand(Object referential, PFPoint expandPoint) {
        expandTo(referential, expandPoint, new PFPoint(expandPoint.x + 1, expandPoint.y));
        expandTo(referential, expandPoint, new PFPoint(expandPoint.x - 1, expandPoint.y));
        expandTo(referential, expandPoint, new PFPoint(expandPoint.x, expandPoint.y + 1));
        expandTo(referential, expandPoint, new PFPoint(expandPoint.x, expandPoint.y - 1));
    }

    protected void expandTo(Object referential, PFPoint from, PFPoint to) {
        float price = map.getEdgeEvaluation(from.x, from.y, to.x, to.y, referential);
        if(price < 0) {
            return;
        }
        if(prices[to.x][to.y] >= 0 && prices[to.x][to.y] <= price) {
            return;
        }
        prices[to.x][to.y] = price;
        addToExpand(to);
    }
    
    protected void addToExpand(PFPoint toExpand) {
        //TODO:
    }
}
