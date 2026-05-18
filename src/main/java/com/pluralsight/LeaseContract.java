package com.pluralsight;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String email, Vehicle vehicleSold, double expectedEndingValue, double leaseFee) {
        super(date, customerName, email, vehicleSold);
        this.setExpectedEndingValue();
        this.setLeaseFee();
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue() {
        this.expectedEndingValue = this.getVehicleSold().getPrice() * 0.5;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee() {
        this.leaseFee = this.getVehicleSold().getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return this.getMonthlyPayment() * 36 + this.leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return ((this.getVehicleSold().getPrice() * 0.04) + this.getVehicleSold().getPrice()) / 36;
    }

    @Override
    public String toString() {
        Vehicle vehicle = this.getVehicleSold();

        return "LEASE"
                + '|' + this.getDate()
                + '|' + this.getCustomerName()
                + '|' + this.getEmail()
                + '|' + vehicle.getVin()
                + '|' + vehicle.getYear()
                + '|' + vehicle.getMake()
                + '|' + vehicle.getModel()
                + '|' + vehicle.getVehicleType()
                + '|' + vehicle.getColor()
                + '|' + vehicle.getOdometer()
                + '|' + vehicle.getPrice()
                + '|' + this.expectedEndingValue
                + '|' + this.leaseFee
                + '|' + this.getTotalPrice()
                + '|' + this.getMonthlyPayment();
    }
}
