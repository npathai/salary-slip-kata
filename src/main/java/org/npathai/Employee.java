package org.npathai;

import java.math.BigDecimal;

public class Employee {
    private long id;
    private String name;
    private BigDecimal annualSalary;

    public Employee(long id, String name, BigDecimal annualSalary) {
        this.id = id;
        this.name = name;
        this.annualSalary = annualSalary;
    }

    public long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public BigDecimal annualSalary() {
        return annualSalary;
    }
}
