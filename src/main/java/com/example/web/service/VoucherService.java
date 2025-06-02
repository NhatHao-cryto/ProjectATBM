package com.example.web.service;

import com.example.web.dao.VoucherDao;
import com.example.web.dao.model.Voucher;

import java.sql.SQLException;
import java.util.*;

public class VoucherService {
    private VoucherDao voucherDao = new VoucherDao();

    public List<Voucher> getAll() throws SQLException {
        return  voucherDao.getAll();
    }
    public List<Voucher> getAllAdmin() throws SQLException {
        return  voucherDao.getAllAdmin();
    }

    public Voucher getVoucherById(String vid) throws SQLException {
        return voucherDao.getVoucherById(vid);
    }

    public boolean deleteVoucher(String vid) throws SQLException {
        return voucherDao.deleteVoucher(vid);
    }
    public boolean addVoucher(Voucher voucher) throws SQLException {
        return voucherDao.addVoucher(voucher);
    }
    public boolean updateVoucher(Voucher voucher) throws SQLException {
        return voucherDao.updateVoucher(voucher);
    }

}
