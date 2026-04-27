import { Canvas } from "@react-three/fiber";
import { useNavigate } from "react-router-dom";
import { OrbitControls } from "@react-three/drei";
import { useState, useRef } from "react";
import Model from "./model.jsx";
import Hotspots from "./hotspots.jsx";

export default function RightSide() {
    const hasSeen = sessionStorage.getItem("sashaPlayed") === "true";
    const [showPoints, setShowPoints] = useState(hasSeen);
    const [isFront, setIsFront] = useState(true);
    const controlsRef = useRef();
    const navigate = useNavigate();

    const toggleView = () => {
        if (controlsRef.current) {
            controlsRef.current.setAzimuthalAngle(isFront ? Math.PI : 0);
            controlsRef.current.update();
        }
        setIsFront(prev => !prev);
    };

    const handleSelect = (muscle) => {
        navigate(`/exercises/${encodeURIComponent(muscle)}`);
    };

    return (
        <div style={styles.container}>
            <Canvas camera={{ position: [0, 1.5, 4], fov: 50 }}>
                <ambientLight />
                <directionalLight position={[5, 5, 5]} />

                <OrbitControls
                    ref={controlsRef}
                    enableZoom={false}
                    enablePan={false}
                    enableRotate={false}
                />

                <Model
                    playAnimation={!hasSeen}
                    onAnimationEnd={() => {
                        setShowPoints(true);
                        sessionStorage.setItem("sashaPlayed", "true");
                    }}
                />

                {showPoints && <Hotspots onSelect={handleSelect} />}
            </Canvas>

            {showPoints && (
                <button onClick={toggleView} style={styles.button}>
                    {isFront ? "View Back →" : "← View Front"}
                </button>
            )}
        </div>
    );
}

const styles = {
    container: {
        flex: 1,
        height: "100%",
        position: "relative",
        overflow: "hidden",
        background: `
          radial-gradient(circle at 50% 35%, 
            #1e293b 0%, 
            #0f172a 40%, 
            #020617 100%
          )
        `,
    },
    button: {
        position: "absolute",
        bottom: "24px",
        left: "50%",
        transform: "translateX(-50%)",
        background: "rgba(255,255,255,0.1)",
        border: "1px solid rgba(255,255,255,0.2)",
        color: "white",
        padding: "8px 20px",
        borderRadius: "20px",
        cursor: "pointer",
        fontSize: "14px",
        backdropFilter: "blur(8px)",
        zIndex: 999,
    },
};

