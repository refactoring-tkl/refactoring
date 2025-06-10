package com.tkl.refactoring.chapter3.element21;

interface ApiCallable<T extends ResultWrapper> {
	T call(String method, Object param);
}
