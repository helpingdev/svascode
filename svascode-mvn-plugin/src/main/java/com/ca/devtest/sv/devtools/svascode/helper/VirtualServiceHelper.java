package com.ca.devtest.sv.devtools.svascode.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.yaml.snakeyaml.Yaml;

import com.ca.devtest.sv.devtools.svascode.model.VirtualServiceModel;
import com.ca.devtest.sv.devtools.svascode.model.VirtualServicesModel;
import com.google.common.io.ByteStreams;

public class VirtualServiceHelper {

	private static final Yaml yaml = new Yaml();

	/**
	 * parse yaml file and return liste of services
	 * 
	 * @param yamlFile
	 * @return liste of services to deploy
	 */
	public static VirtualServicesModel loadVirtualServiceDefinitions(File yamlFile) {
		VirtualServicesModel result = null;

		try {
			result = yaml.loadAs(new ByteArrayInputStream(ByteStreams.toByteArray(new FileInputStream(yamlFile))),
					VirtualServicesModel.class);
			propagateDefaultValue(result);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static void propagateDefaultValue(VirtualServicesModel result) {
		
		List<VirtualServiceModel> services= result.getServices();
		for (VirtualServiceModel virtualServiceModel : services) {
			if(StringUtils.isEmpty(virtualServiceModel.getDefinition())){
				virtualServiceModel.setDefinition(result.getDefinition());
			}
		}
		
	}
}
