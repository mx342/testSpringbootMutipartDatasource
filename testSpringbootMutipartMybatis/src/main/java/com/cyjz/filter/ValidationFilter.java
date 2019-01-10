package com.cyjz.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;

public class ValidationFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ValidationFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		Properties prop = new Properties();
		InputStream in = ValidationFilter.class
				.getResourceAsStream("/config/ipwhitelist.properties");
		String clientIp = RpcContext.getContext().getRemoteHost();// 客户端ip
		try {
			prop.load(in);
			String ipwhitelist = prop.getProperty("ipwhitelist");// ip白名单
			if (ipwhitelist.contains(clientIp)) {
				return invoker.invoke(invocation);
			} else {
				return new RpcResult(new Exception("ip地址：" + clientIp
						+ "没有访问权限"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RpcException e) {
			throw e;
		} catch (Throwable t) {
			throw new RpcException(t.getMessage(), t);
		}
		return invoker.invoke(invocation);
	}

}
