package com.duncan.blog.controller;

import com.duncan.blog.utils.MapCache;

public abstract class BaseController {
	
	protected MapCache cache = MapCache.single(); 
	
}
