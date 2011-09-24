package br.com.wbotelhos.starting.helper;

import org.jstryker.database.DBUnitHelper;

public class Helper {

	private static final String PACKAGE = "/br/com/wbotelhos/starting/integration/xml/";
	private static final String[] DATASETS = { "Usuario", "UsuarioImage" };

	private final DBUnitHelper dbUnitHelper;

	public Helper(DBUnitHelper dbUnitHelper) {
		this.dbUnitHelper = dbUnitHelper;
	}

	public Helper cleanAndInsert(String... datasets) {
		return clean(datasets).insert(datasets);
	}

	public Helper clean(String... datasets) {
		if (datasets.length == 0) {
			datasets = DATASETS;
		}

		for (int i = datasets.length - 1; i >= 0; i--) {
			dbUnitHelper.deleteAll(PACKAGE + datasets[i] + ".xml");
		}

		return this;
	}

	public Helper insert(String... datasets) {
		if (datasets.length == 0) {
			datasets = DATASETS;
		}

		for (String dataset : datasets) {
			dbUnitHelper.insert(PACKAGE + dataset + ".xml");
		}

		return this;
	}

	public static String[] getDatasets() {
		return DATASETS;
	}

}