import { Html } from "@react-three/drei";
import { useState } from "react";
import { useThree } from "@react-three/fiber";
import useBodyParts from "../hooks/useBodyParts.js";
import { hotspotConfig } from "../constants/hotspotConfig.js";

export default function Hotspots({ onSelect }) {
    const bodyParts = useBodyParts();

    return (
        <>
            {bodyParts.map((bp) => {
                const config = hotspotConfig[bp.name];

                if (!config) return null;

                return (
                    <Point
                        key={bp.id}
                        position={config.position}
                        side={config.side}
                        label={bp.name}
                        onSelect={onSelect}
                    />
                );
            })}
        </>
    );
}

function Point({ position, label, side = "right", onSelect }) {
    const [hovered, setHovered] = useState(false);
    const { gl } = useThree();
    const offset = side === "right" ? 0.4 : -0.5;
    const textAlign = side === "right" ? "left" : "right";

    return (
        <group position={position}>
            <mesh
                scale={hovered ? 1.4 : 1}
                onClick={() => onSelect(label)}
                onPointerOver={(e) => {
                    e.stopPropagation();
                    setHovered(true);
                    gl.domElement.style.cursor = "pointer";
                }}
                onPointerOut={() => {
                    setHovered(false);
                    gl.domElement.style.cursor = "default";
                }}
            >
                <sphereGeometry args={[0.025]} />
                <meshStandardMaterial
                    color={hovered ? "#fde047" : "#facc15"}
                    emissive={hovered ? "#fde047" : "#facc15"}
                    emissiveIntensity={hovered ? 1.5 : 0.7}
                />
            </mesh>

            <mesh position={[offset / 2, 0, 0]}>
                <boxGeometry args={[Math.abs(offset), 0.005, 0.005]} />
                <meshStandardMaterial color="rgba(255,255,255,0.5)" />
            </mesh>

            <Html position={[offset, 0, 0]}>
                <div style={{ ...styles.label, textAlign }}>
                    {label}
                </div>
            </Html>
        </group>
    );
}

const styles = {
    label: {
        color: "white",
        fontSize: "14px",
        whiteSpace: "nowrap",
        fontWeight: "500",
    },
};