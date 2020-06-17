module DataAccess {
	
	requires transitive java.sql;
	requires transitive Entities;
	exports dataAccessApi;
	exports jdbcImpl;
}