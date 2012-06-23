/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.pathfinder;

import java.util.ArrayList;

/**
 *
 * @author indy
 */
public class PFPath {

    protected PFPoint[] path = null;
    protected Object referential = null;

    public PFPath(ArrayList<PFPoint> path, Object referential) {
        this.referential = referential;
        if (path != null) {
            this.path = new PFPoint[path.size()];
            for (int i = (path.size() - 1); i >= 0; i--) {
                this.path[this.path.length - i - 1] = path.get(i);
            }
        }
    }

    public PFPoint getStart() {
        if (path == null) {
            return null;
        }
        return path[0];
    }

    public PFPoint getEnd() {
        if (path == null) {
            return null;
        }
        return path[path.length - 1];
    }

    public PFPoint getStep(int step) {
        if (path == null) {
            return null;
        }
        if (step < 0 || step >= path.length) {
            return null;
        }
        return path[step];
    }

    public PFPoint[] getPath() {
        return path;
    }

    public Object getReferentialObject() {
        return referential;
    }
}
