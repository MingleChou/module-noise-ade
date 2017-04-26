package org.citygml.ade.noise.model;

import org.citygml.ade.noise.NoiseADEContext;
import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.citygml.ade.binding.ADEGenericApplicationProperty;
import org.citygml4j.model.gml.basicTypes.Measure;

public class BuildingLNightMaxProperty extends ADEGenericApplicationProperty<Measure> {

	public BuildingLNightMaxProperty() {
	}
	
	public BuildingLNightMaxProperty(Measure value) {
		super(value);
	}
	
	@Override
	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new BuildingLNightMaxProperty(), copyBuilder);
	}

	@Override
	public String getNamespaceURI() {
		return NoiseADEContext.NAMESPACE_URI;
	}
	
}