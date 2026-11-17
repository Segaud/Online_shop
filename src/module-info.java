/**
 * 
 */
/**
 * 
 */
module Practise {
	requires java.sql;
	requires static org.junit.platform.console.standalone;

	opens online_shop to org.junit.platform.console.standalone;
}