package dao;

import java.util.Date;
import java.util.List;

import entities.Driver;
import entities.SalaryRequest;
import entities.RequestStatus;

public interface SalaryRequestDAO {
	public void create(SalaryRequest request);
	public void update(SalaryRequest request);
	public void delete(SalaryRequest request);
	public List<SalaryRequest> findAll();
	public SalaryRequest find(int id);
	public List<SalaryRequest> findByStatus(RequestStatus status);
	public long count();
	public List<SalaryRequest> findInRange(long start, long end);
	public List<SalaryRequest> findByStatusInRange(RequestStatus status, long start, long end);
	public long countByStatus(RequestStatus status);
	public Double countDirtySalary(Date start, Date end, Driver driv);
}
