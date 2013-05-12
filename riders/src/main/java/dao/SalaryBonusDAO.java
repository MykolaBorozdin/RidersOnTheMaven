package dao;

import java.util.List;

import entities.SalaryBonus;

public interface SalaryBonusDAO {

	public void create(SalaryBonus bonus);
	public void update(SalaryBonus bonus);
	public void delete(SalaryBonus bonus);
	public List<SalaryBonus> findAll();
	public SalaryBonus find(int id);
	public long count();
}
