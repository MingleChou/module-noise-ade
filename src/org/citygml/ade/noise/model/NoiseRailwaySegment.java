package org.citygml.ade.noise.model;

import java.util.List;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.builder.copy.ObjectCopier;
import org.citygml4j.model.citygml.ade.binding.ADEModelObject;
import org.citygml4j.model.citygml.core.LodRepresentation;
import org.citygml4j.model.citygml.transportation.AbstractTransportationObject;
import org.citygml4j.model.common.child.ChildList;
import org.citygml4j.model.common.visitor.FeatureFunctor;
import org.citygml4j.model.common.visitor.FeatureVisitor;
import org.citygml4j.model.common.visitor.GMLFunctor;
import org.citygml4j.model.common.visitor.GMLVisitor;
import org.citygml4j.model.gml.basicTypes.Measure;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.CurveProperty;
import org.citygml4j.model.gml.measures.Length;

public class NoiseRailwaySegment extends AbstractTransportationObject implements ADEModelObject {
	private String railwaySurfaceMaterial;
	private Measure railwaySurfaceCorrection;
	private Boolean bridge;
	private Boolean crossing;
	private Length curveRadius;
	private Measure additionalCorrectionSegment;
	private CurveProperty lod0BaseLine;
	private List<TrainProperty> usedBy;
	
	public String getRailwaySurfaceMaterial() {
		return railwaySurfaceMaterial;
	}
	
	public boolean isSetRailwaySurfaceMaterial() {
		return railwaySurfaceMaterial != null;
	}

	public void setRailwaySurfaceMaterial(String railwaySurfaceMaterial) {
		this.railwaySurfaceMaterial = railwaySurfaceMaterial;
	}

	public Measure getRailwaySurfaceCorrection() {
		return railwaySurfaceCorrection;
	}
	
	public boolean isSetRailwaySurfaceCorrection() {
		return railwaySurfaceCorrection != null;
	}

	public void setRailwaySurfaceCorrection(Measure railwaySurfaceCorrection) {
		if (railwaySurfaceCorrection != null)
			railwaySurfaceCorrection.setParent(this);
		
		this.railwaySurfaceCorrection = railwaySurfaceCorrection;
	}

	public Boolean getBridge() {
		return bridge;
	}
	
	public boolean isSetBridge() {
		return bridge != null;
	}

	public void setBridge(Boolean bridge) {
		this.bridge = bridge;
	}

	public Boolean getCrossing() {
		return crossing;
	}
	
	public boolean isSetCrossing() {
		return crossing != null;
	}

	public void setCrossing(Boolean crossing) {
		this.crossing = crossing;
	}

	public Length getCurveRadius() {
		return curveRadius;
	}
	
	public boolean isSetCurveRadius() {
		return curveRadius != null;
	}

	public void setCurveRadius(Length curveRadius) {
		if (curveRadius != null)
			curveRadius.setParent(this);
		
		this.curveRadius = curveRadius;
	}

	public Measure getAdditionalCorrectionSegment() {
		return additionalCorrectionSegment;
	}
	
	public boolean isSetAdditionalCorrectionSegment() {
		return additionalCorrectionSegment != null;
	}

	public void setAdditionalCorrectionSegment(Measure additionalCorrectionSegment) {
		if (additionalCorrectionSegment != null)
			additionalCorrectionSegment.setParent(this);
		
		this.additionalCorrectionSegment = additionalCorrectionSegment;
	}

	public CurveProperty getLod0BaseLine() {
		return lod0BaseLine;
	}
	
	public boolean isSetLod0BaseLine() {
		return lod0BaseLine != null;
	}

	public void setLod0BaseLine(CurveProperty lod0BaseLine) {
		if (lod0BaseLine != null)
			lod0BaseLine.setParent(this);
		
		this.lod0BaseLine = lod0BaseLine;
	}

	public void addUsedBy(TrainProperty usedBy) {
		if (this.usedBy == null)
			this.usedBy = new ChildList<>(this);
		
		this.usedBy.add(usedBy);
	}
	
	public List<TrainProperty> getUsedBy() {
		if (usedBy == null)
			usedBy = new ChildList<>(this);
		
		return usedBy;
	}
	
	public boolean isSetUsedBy() {
		return usedBy != null && !usedBy.isEmpty();
	}

	public void setUsedBy(List<TrainProperty> usedBy) {
		this.usedBy = new ChildList<>(this, usedBy);
	}
	
	@Override
	public BoundingShape calcBoundedBy(boolean setBoundedBy) {
		if (lod0BaseLine != null && lod0BaseLine.isSetCurve()) {
			BoundingShape boundedBy = new BoundingShape();
			calcBoundedBy(boundedBy, lod0BaseLine.getCurve());
			
			if (boundedBy.isSetEnvelope()) {
				if (setBoundedBy)
					setBoundedBy(boundedBy);
				
				return boundedBy;
			}
		}
		
		return null;
	}
	
	@Override
	public LodRepresentation getLodRepresentation() {
		LodRepresentation lodRepresentation = new LodRepresentation();
		
		if (lod0BaseLine != null)
			lodRepresentation.getLod0Geometry().add(lod0BaseLine);
		
		return lodRepresentation;
	}

	@Override
	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new NoiseRailwaySegment(), copyBuilder);
	}
	
	@Override
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		NoiseRailwaySegment copy = (target == null) ? new NoiseRailwaySegment() : (NoiseRailwaySegment)target;
		super.copyTo(copy, copyBuilder);
		
		return new ObjectCopier().copyTo(this, copy, copyBuilder);
	}

	@Override
	public void accept(FeatureVisitor visitor) {
		visitor.visit((ADEModelObject)this);
	}

	@Override
	public <T> T accept(FeatureFunctor<T> visitor) {
		return visitor.apply((ADEModelObject)this);
	}

	@Override
	public void accept(GMLVisitor visitor) {
		visitor.visit((ADEModelObject)this);
	}

	@Override
	public <T> T accept(GMLFunctor<T> visitor) {
		return visitor.apply((ADEModelObject)this);
	}

}
