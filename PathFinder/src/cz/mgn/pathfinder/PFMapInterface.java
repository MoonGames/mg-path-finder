/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.pathfinder;

/**
 *
 * @author indy
 */
public interface PFMapInterface {

    public float getEdgeEvaluation(int xStart, int yStart, int xEnd, int yEnd, Object referential);
}
