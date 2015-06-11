package org.loda.ioc.exception;

/**
 * 
* @ClassName: PackageNotFoundException 
* @Description: 包没有找到 
* @author minjun
* @date 2015年6月9日 上午9:59:04 
*
 */
public class PackageNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6822101639626216135L;

	public PackageNotFoundException() {
		super();
	}

	public PackageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PackageNotFoundException(String message) {
		super(message);
	}

	public PackageNotFoundException(Throwable cause) {
		super(cause);
	}

}
