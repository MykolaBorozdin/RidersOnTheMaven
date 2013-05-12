package dao;

import java.util.List;

import entities.SalaryTax;

public interface SalaryTaxDAO {
	public void create(SalaryTax tax);
	public void update(SalaryTax tax);
	public void delete(SalaryTax tax);
	public List<SalaryTax> findAll();
	public SalaryTax find(int id);
	public long count();
}
