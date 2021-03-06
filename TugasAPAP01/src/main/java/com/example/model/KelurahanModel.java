package com.example.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class KelurahanModel {
	private String id;
	private String kode_kelurahan;
	private String id_kecamatan;
	private String nama_kelurahan;
	private String kode_pos;
	private String id_kota;
	private String nama_kota;
	private String nama_kecamatan;
	private String kode_kecamatan;
	private String kode_kota;
}
