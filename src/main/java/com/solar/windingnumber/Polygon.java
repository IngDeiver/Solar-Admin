/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solar.windingnumber;

import org.springframework.stereotype.Component;

/**
 *
 * @author teleupb
 * @source http://geomalgorithms.com/a03-_inclusion.html#wn_PnPoly()
 */
@Component
public class Polygon {
    private Point[] points;

    public Polygon() {
    }

    public Polygon(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
    
    private double isLeft(Point P0, Point P1, Point P2){
        return ( (P1.x - P0.x) * (P2.y - P0.y)
            - (P2.x -  P0.x) * (P1.y - P0.y) );
    }
    public int inPolygon(Point P){
        int wn = 0;
        int n = points.length;
        for (int i=0; i<n; i++) {   // edge from points[i] to  points[i+1]
        int j = i+1;
        if (j>=n)
            j = 0;
        if (points[i].y <= P.y) {          // start y <= P.y
            if (points[j].y  > P.y)      // an upward crossing
                 if (isLeft( points[i], points[j], P) > 0)  // P left of  edge
                     ++wn;            // have  a valid up intersect
        }
        else {                        // start y > P.y (no test needed)
            if (points[j].y  <= P.y)     // a downward crossing
                 if (isLeft( points[i], points[j], P) < 0)  // P right of  edge
                     --wn;            // have  a valid down intersect
        }
    }
    return wn;
    }
    
}
