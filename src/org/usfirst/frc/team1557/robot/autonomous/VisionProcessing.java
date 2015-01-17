//package org.usfirst.frc.team1557.robot.autonomous;
//
//import java.util.Collection;
//
//import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.Connectivity;
//import com.ni.vision.NIVision.Image;
//import com.ni.vision.NIVision.MeasurementType;
//import com.ni.vision.NIVision.PaletteType;
//import com.ni.vision.NIVision.ParticleReport;
//
//import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
//
//public class VisionProcessing {
//	public static ParticleAnalysisReport vaParticleReport;
//	public static ParticleAnalysisReport vaParticleReportCalibrated;
//
//	private static void IVA_Particle(Image image,
//									Connectivity connectivity,
//									Collection<MeasurementType> pPixelMeasurements,
//									Collection<MeasurementType> pCalibratedMeasurements,
//									//IVA_Data ivaData,
//									int stepIndex)
//									//ParticleMeasurementsReport partReport,
//									//ParticleMeasurementsReport partReportCal)
//	{
//
//		// Computes the requested pixel measurements.
//		ParticleReport partReport;
//		if (pPixelMeasurements.size() != 0)
//		{
//			partReport = NIVision.imaqMeasureParticle(image, pPixelMeasurements, connectivity, ParticleMeasurementsCalibrationMode.Pixel);
//		}
//		else
//		{
//			partReport = new NIVision.ParticleReport();//new ParticleMeasurementsReport();
//		}
//		
//		// Computes the requested calibrated measurements.
//		if (pCalibratedMeasurements.size() != 0)
//		{
//			
//			partReportCal = Algorithms.ParticleMeasurements(image, pCalibratedMeasurements, connectivity, ParticleMeasurementsCalibrationMode.Calibrated);
//		}
//		else
//		{
//			partReportCal = new ParticleMeasurementsReport();
//		}
//		
//		// Computes the center of mass of each particle to log as results.
//		ParticleMeasurementsReport centerOfMass;
//		Collection<MeasurementType> centerOfMassMeasurements = new Collection<MeasurementType>();
//		centerOfMassMeasurements.Add(MeasurementType.CenterOfMassX);
//		centerOfMassMeasurements.Add(MeasurementType.CenterOfMassY);
//		
//		if ((image.InfoTypes & InfoTypes.Calibration) != 0)
//		{
//			centerOfMass = Algorithms.ParticleMeasurements(image, centerOfMassMeasurements, connectivity, ParticleMeasurementsCalibrationMode.Both);
//		}
//		else
//		{
//			centerOfMass = Algorithms.ParticleMeasurements(image, centerOfMassMeasurements, connectivity, ParticleMeasurementsCalibrationMode.Pixel);
//		}
//		
//		// Delete all the results of this step (from a previous iteration)
//		Functions.IVA_DisposeStepResults(ivaData, stepIndex);
//		
//		ivaData.stepResults[stepIndex].results.Add(new IVA_Result("Object #", centerOfMass.PixelMeasurements.GetLength(0)));
//		
//		if (centerOfMass.PixelMeasurements.GetLength(0) > 0)
//		{
//			for(int i = 0; i < centerOfMass.PixelMeasurements.GetLength(0); ++i)
//			{
//				ivaData.stepResults[stepIndex].results.Add(new IVA_Result(String.Format("Particle {0}.X Position (Pix.)", i+1), centerOfMass.PixelMeasurements[i, 0]));
//				ivaData.stepResults[stepIndex].results.Add(new IVA_Result(String.Format("Particle {0}.Y Position (Pix.)", i+1), centerOfMass.PixelMeasurements[i, 1]));
//				
//				// If the image is calibrated, also store the real world coordinates.
//				if ((image.InfoTypes & InfoTypes.Calibration) != 0)
//				{
//					ivaData.stepResults[stepIndex].results.Add(new IVA_Result(String.Format("Particle {0}.X Position (Calibrated)", i+1), centerOfMass.CalibratedMeasurements[i, 0]));
//					ivaData.stepResults[stepIndex].results.Add(new IVA_Result(String.Format("Particle {0}.Y Position (Calibrated)", i+1), centerOfMass.CalibratedMeasurements[i, 1]));
//				}
//			}
//		}
//	}
//
//	public static PaletteType ProcessImage(VisionImage image)
//    {
//        // Initialize the IVA_Data structure to pass results and coordinate systems.
//		IVA_Data ivaData = new IVA_Data(4, 0);
//		
//		// Extract Color Plane
//		import (VisionImage plane = new VisionImage(ImageType.U8, 7))
//		{
//			// Extract the green color plane and copy it to the main image.
//			Algorithms.ExtractColorPlanes(image, ColorMode.Rgb, null, plane, null);
//			Algorithms.Copy(plane, image);
//		}
//		
//		// Manual Threshold
//		Algorithms.Threshold(image, image, new Range(165, 255), true, 1);
//		
//		// Advanced Morphology: Label - Labels the particles in a binary image by applying a unique value to all pixels within a particle.
//		//Algorithms.Label(image, image, Connectivity.Connectivity4);
//		
//		// Particle Analysis - Computes the number of particles detected in a binary image and
//		// returns the requested measurements about the particles.
//		Collection<MeasurementType> vaPixelMeasurements = new Collection<MeasurementType>(new MeasurementType[]{MeasurementType.CenterOfMassX, MeasurementType.CenterOfMassY, MeasurementType.Area});
//		Collection<MeasurementType> vaCalibratedMeasurements = new Collection<MeasurementType>(new MeasurementType[]{});
//		IVA_Particle(image, Connectivity.Connectivity8, vaPixelMeasurements, vaCalibratedMeasurements, ivaData, 3, out vaParticleReport, out vaParticleReportCalibrated);
//		
//		// Dispose the IVA_Data structure.
//		ivaData.Dispose();
//		
//		// Return the palette type of the final image.
//		return PaletteType.Binary;
//
//    }
//}
