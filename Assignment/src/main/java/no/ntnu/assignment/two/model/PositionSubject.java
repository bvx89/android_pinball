package no.ntnu.assignment.two.model;

/**
 * Created by bvx89 on 2/5/14.
 */
public interface PositionSubject {
    public void addPositionListener(PositionListener p);
    public void removePositionListener(PositionListener p);
    public void notifyListeners();
}
