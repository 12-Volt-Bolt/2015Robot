package org.usfirst.frc.team1557.robot;

public enum AutonomousPlans {
	/**
	 * No action
	 */
	NO_OP,
	/**
	 * Move into Autozone Only with bump
	 */
	BUMP_DRIVE_ONLY,
	/**
	 * Move into Autozone with no bump
	 */
	BUMPLESS_DRIVE_ONLY,
	/**
	 * Picks up only the left Bin
	 */
	LEFT_BIN_ONLY,
	/**
	 * Picks up only the center bin
	 */
	CENTER_BIN_ONLY,
	/**
	 * Picks up only the Right Bin
	 */
	RIGHT_BIN_ONLY,
	/**
	 * Picks up both Left objects
	 */
	LEFT_BOTH,
	/*
	 * Picks up both Center objects
	 */
	CENTER_BOTH,
	/**
	 * Picks up both Right objects
	 */
	RIGHT_BOTH,
	/**
	 * Picks up the left then center tote
	 */
	LEFT_CENTER_TOTE,
	/**
	 * Picks up the center then left tote
	 */
	CENTER_LEFT_TOTE,
	/**
	 * Picks up the center then right tote
	 */
	CENTER_RIGHT_TOTE,
	/**
	 * Picks up the right then center tote
	 */
	RIGHT_CENTER_TOTE,
	/**
	 * RETRIEVES THE TRIFORCE. FEARD AMONG OTHER AUTONOMI. <br/>
	 * PRAISE GABEN. GABEN BE PRAISED. <br/>
	 * Triforce^3 Confirmed
	 */
	RIGHT_TRIFORCE,

}
