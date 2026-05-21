package tamaized.datagenutil.assets.lang;

import tamaized.pkginfoutil.PublicApi;

public interface LangProvider {

	@PublicApi
	void addLangEntries(ExtendedLangProvider provider);

}
