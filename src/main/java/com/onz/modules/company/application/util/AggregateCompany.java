package com.onz.modules.company.application.util;

import com.onz.modules.review.domain.CompanyReview;

public class AggregateCompany {
    private long count = 0;
    private double likeCodeSum = 0;
    private double itemB1Sum = 0;
    private double itemB2Sum = 0;
    private double itemB3Sum = 0;
    private double itemC1Sum = 0;
    private double itemC2Sum = 0;
    private double itemC3Sum = 0;
    private double itemD1Sum = 0;
    private double itemD2Sum = 0;

    public void merge(AggregateCompany other){
        this.count += other.count;
        this.likeCodeSum += other.likeCodeSum;
        this.itemB1Sum += other.itemB1Sum;
        this.itemB2Sum += other.itemB2Sum;
        this.itemB3Sum += other.itemB3Sum;
        this.itemC1Sum += other.itemC1Sum;
        this.itemC2Sum += other.itemC2Sum;
        this.itemC3Sum += other.itemC3Sum;
        this.itemD1Sum += other.itemD1Sum;
        this.itemD2Sum += other.itemD2Sum;
    }
    public void add(CompanyReview rv){
        this.count+=1;
        this.likeCodeSum += rv.getLikeCode().getScore();
        this.itemB1Sum += rv.getItemB1().getScore();
        this.itemB2Sum += rv.getItemB2().getScore();
        this.itemB3Sum += rv.getItemB3().getScore();
        this.itemC1Sum += rv.getItemC1().getScore();
        this.itemC2Sum += rv.getItemC2().getScore();
        this.itemC3Sum += rv.getItemC3().getScore();
        this.itemD1Sum += rv.getItemD1().getScore();
        this.itemD2Sum += rv.getItemD2().getScore();
    }
    public String toStringSum(){
        String rs = this.likeCodeSum +", "
                + this.itemB1Sum +", " + this.itemB2Sum +", " + this.itemB3Sum +", "
                + this.itemC1Sum +", " + this.itemC2Sum +", " + this.itemC3Sum +", "
                + this.itemD1Sum +", " + this.itemD2Sum;
        return rs;
    }
    public double getAvgTotal(){
        double totalSum = this.likeCodeSum
                + this.itemB1Sum + this.itemB2Sum + this.itemB3Sum
                + this.itemC1Sum + this.itemC2Sum + this.itemC3Sum
                + this.itemD1Sum + this.itemD2Sum;
        return Math.round(totalSum / this.count / 9 * 10) / 10 ;
    }
    public double getAvgLikeCode(){
        return Math.round(this.likeCodeSum / this.count * 10) / 10 ;
    }
    public double getAvgItemB1(){
        return Math.round(this.itemB1Sum / this.count * 10) / 10;
    }
    public double getAvgItemB2(){
        return Math.round(this.itemB2Sum / this.count * 10) / 10;
    }
    public double getAvgItemB3(){
        return Math.round(this.itemB3Sum / this.count * 10) / 10;
    }
    public double getAvgItemC1(){
        return Math.round(this.itemC1Sum / this.count * 10) / 10;
    }
    public double getAvgItemC2(){
        return Math.round(this.itemC2Sum / this.count * 10) / 10;
    }
    public double getAvgItemC3(){
        return Math.round(this.itemC3Sum / this.count * 10) / 10;
    }
    public double getAvgItemD1(){
        return Math.round(this.itemD1Sum / this.count * 10) / 10;
    }
    public double getAvgItemD2(){
        return Math.round(this.itemD2Sum / this.count * 10) / 10;
    }
}
