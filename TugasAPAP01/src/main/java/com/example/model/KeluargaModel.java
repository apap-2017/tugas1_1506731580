package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.StringTokenizer;

import com.example.service.KeluargaService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class KeluargaModel {
	private Integer id_keluarga;
	private String nomor_kk;
	private String alamat;
	private String RT;
	private String RW;
	private String nama_kota;
	private String kode_kecamatan;
	private String nama_kelurahan;
	private String nama_kecamatan;
	private String id_kelurahan;
	private String is_tidak_berlaku;
	List<PendudukModel> warga;

	public void generateNkk(KeluargaService keluargaDAO) {
		StringTokenizer tanggalNowTokenizer = new StringTokenizer(new SimpleDateFormat("yy-MM-dd").format(new Date()), "-");
		String tahunNow = tanggalNowTokenizer.nextToken().substring(2);
		String bulanNow = tanggalNowTokenizer.nextToken();
		String tanggalNow = tanggalNowTokenizer.nextToken();
		
		String kecamatan = this.getKode_kecamatan().substring(0,6);
		int i = 1;
		String nomor_kk = kecamatan+ tanggalNow + bulanNow + tahunNow+"0000"+i;
		
		KeluargaModel flag = keluargaDAO.selectKeluarga(nomor_kk);

		while(flag!=null) {
			i++;
			nomor_kk = kecamatan + tanggalNow + bulanNow + tahunNow+"0000"+ i;
		}
		
		this.setNomor_kk(nomor_kk);
	}


	
}
