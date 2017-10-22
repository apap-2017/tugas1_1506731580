package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.service.KeluargaService;
import com.example.service.PendudukService;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PendudukModel {
	private Integer id;
	private String nik;
	private String nama;
	private String tempat_lahir;
	private String tanggal_lahir;
	private String jenis_kelamin;
	private String is_wni;
	private String agama;
	private String pekerjaan;
	private String status_perkawinan;
	private String status_dalam_keluarga;
	private String golongan_darah;
	private String is_wafat;
	private String id_keluarga;

	public void generateNik(KeluargaService keluargaDAO, PendudukService pendudukDAO) {

		KeluargaModel keluarga = keluargaDAO.getKeluarga(this.getId_keluarga());
		String nik = keluarga.getKode_kecamatan().substring(0,6);
		String[] tgl = this.getTanggal_lahir().split("-");
		nik += tgl[0].substring(2)+tgl[1];
		int tanggal = Integer.parseInt(tgl[2]) + Integer.parseInt(this.getJenis_kelamin())*40;
		if(tanggal<10) nik+="0";
		nik += tanggal;
		
		PendudukModel flag = pendudukDAO.selectPenduduk(nik);
		
		if(flag==null||this.getNik().equals(flag.getNik())) {
			nik += "0001";
		}
		else {
			long flags = Long.parseLong(flag.getNik())+1;
			nik = flags+"";
		}
		this.setNik(nik);
	}
}