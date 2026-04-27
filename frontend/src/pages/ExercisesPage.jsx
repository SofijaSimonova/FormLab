import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import exerciseRepository from "../repository/exerciseRepository";
import { Canvas } from "@react-three/fiber";
import { OrbitControls } from "@react-three/drei";
import Model from "../components/model";
import Hotspots from "../components/hotspots.jsx";

const parseDescription = (desc) => {
    if (!desc) return { main: "", avoid: "", tempo: "" };

    let main = desc;
    let avoid = "";
    let tempo = "";

    // Extract Tempo
    if (desc.includes("Tempo:")) {
        const parts = desc.split("Tempo:");
        main = parts[0];
        tempo = parts[1];
    }

    // Extract Avoid
    if (main.includes("Avoid")) {
        const parts = main.split("Avoid");
        main = parts[0];
        avoid = parts[1];
    }

    return {
        main: main.trim(),
        avoid: avoid.replace(":", "").trim(),
        tempo: tempo.trim(),
    };
};

export default function ExercisesPage() {
    const { muscle } = useParams();
    const navigate = useNavigate();
    const [exercises, setExercises] = useState([]);
    const [selectedExercise, setSelectedExercise] = useState(null);
    const getBadgeColor = (difficulty) => {
        if (difficulty === "EASY") return "#f9a8d4";
        if (difficulty === "MEDIUM") return "#ec4899";
        if (difficulty === "HARD") return "#9d174d";
    };
    useEffect(() => {
        fetchExercises();
    }, [muscle]);

    const fetchExercises = async () => {
        try {
            const res = await exerciseRepository.getByBodyPart(muscle);
            setExercises(res.data);
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <div style={styles.container}>
            {/* BACK */}
            <button onClick={() => navigate(-1)} style={styles.back}>
                ← Back
            </button>

            <h2 style={styles.title}>{muscle} Exercises</h2>

            {/* GRID */}
            <div style={styles.grid}>
                {exercises.map((ex) => (
                    <div
                        key={ex.id}
                        style={styles.card}
                        onClick={() => setSelectedExercise(ex)}
                        onMouseEnter={(e) => {
                            e.currentTarget.style.transform = "translateY(-4px)";
                            e.currentTarget.style.background = "rgba(255,255,255,0.08)";
                        }}
                        onMouseLeave={(e) => {
                            e.currentTarget.style.transform = "translateY(0px)";
                            e.currentTarget.style.background = "rgba(255,255,255,0.05)";
                        }}
                    >
                        <h3 style={styles.cardTitle}>{ex.name}</h3>

                        <span style={styles.badge}>
                            {ex.difficulty}
                        </span>
                    </div>
                ))}
            </div>

            {selectedExercise && (
                <div style={styles.detailOverlay}>
                    <div style={styles.detailContent}>
                        <div style={styles.detailLeft}>
                            <h2 style={styles.exerciseTitle}>
                                {selectedExercise.name}
                            </h2>

                            <p style={styles.label}>HOW TO PERFORM</p>

                            {(() => {
                                const parsed = parseDescription(selectedExercise.description);

                                return (
                                    <>
                                        <p style={styles.exerciseDesc}>
                                            {parsed.main}
                                        </p>

                                        {parsed.avoid && (
                                            <p style={styles.avoid}>
                                                ⚠️ Avoid {parsed.avoid}
                                            </p>
                                        )}

                                        {parsed.tempo && (
                                            <p style={styles.tempo}>
                                                ⏰ {parsed.tempo}
                                            </p>
                                        )}
                                    </>
                                );
                            })()}

                            <span
                                style={{
                                    ...styles.badge,
                                    background: getBadgeColor(selectedExercise.difficulty),
                                    marginBottom: "30px" // 🔥 spacing
                                }}
                            >
                                {selectedExercise.difficulty}
                            </span>

                            <button
                                onClick={() => setSelectedExercise(null)}
                                style={styles.closeBtn}
                            >
                                Close
                            </button>
                        </div>
                        <div style={styles.detailRight}>
                            <div style={styles.detailRight}>
                                <Canvas camera={{ position: [0, 1.2, 4], fov: 50 }}>
                                    <ambientLight />
                                    <directionalLight position={[5, 5, 5]} />

                                    <OrbitControls
                                        enableZoom={false}
                                        enablePan={false}
                                    />

                                    <Model
                                        animationName={selectedExercise?.animationName}
                                        rotationY={-0.4}
                                        scale={0.35}
                                    />
                                </Canvas>
                                <div style={styles.hint}>
                                    ⬅ Drag to rotate ➡
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
const styles = {
    container: {
        padding: "40px",
        color: "white",
        background: `
          radial-gradient(circle at 50% 35%, 
            #1e293b 0%, 
            #0f172a 40%, 
            #020617 100%
          )
        `,
        height: "100vh",        // 🔥 наместо minHeight
        boxSizing: "border-box",// 🔥 ВАЖНО
        overflow: "hidden",     // 🔥 убива scroll
    },

    back: {
        marginBottom: "20px",
        background: "rgba(255,255,255,0.1)",
        border: "none",
        padding: "8px 16px",
        borderRadius: "20px",
        color: "white",
        cursor: "pointer",
    },

    title: {
        textAlign: "center",
        marginBottom: "30px",
    },

    grid: {
        display: "grid",
        gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
        gap: "20px",
    },

    card: {
        background: "rgba(255,255,255,0.05)",
        border: "1px solid rgba(255,255,255,0.1)",
        borderRadius: "16px",
        padding: "20px",
        cursor: "pointer",
        transition: "all 0.25s ease",
        backdropFilter: "blur(10px)",
        minHeight: "100px",

        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
    },

    cardTitle: {
        fontSize: "16px",
        fontWeight: "600",
    },

    badge: {
        alignSelf: "center", // 🔥 центар
        fontSize: "11px",
        padding: "4px 10px",
        borderRadius: "999px",
        background: "#f472b6",
        color: "white",
    },

    detailOverlay: {
        position: "fixed",
        top: 0,
        left: 0,
        width: "100%",
        height: "100%",
        background: `
          radial-gradient(circle at 30% 20%, 
            rgba(255, 105, 180, 0.4) 0%, 
            rgba(236, 72, 153, 0.3) 40%, 
            rgba(2, 6, 23, 1) 100%
          )
        `,
        backdropFilter: "blur(12px)",
        display: "flex",
        justifyContent: "center", // 🔥 ОВА Е FIXOT
        zIndex: 1000,
    },

    detailLeft: {
        flex: 1, // 🔥 ОВА Е КЛУЧНО
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        textAlign: "center",
        height: "100%",
        padding: "40px 20px 40px 40px"
    },

    detailRight: {
        flex: 1,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100%",
        padding: "40px 40px 40px 20px"
    },
    exerciseTitle: {
        fontSize: "28px",
        fontWeight: "700",
        marginBottom: "20px",
        color: "white"
    },

    label: {
        fontSize: "12px",
        letterSpacing: "1px",
        opacity: 0.6,
        marginBottom: "6px",
    },

    exerciseDesc: {
        fontSize: "16px",
        lineHeight: "1.6",
        color: "rgba(255,255,255,0.85)",
        marginBottom: "24px",
        maxWidth: "380px", // 🔥 да не се развлекува
    },

    closeBtn: {
        background: "rgba(255,255,255,0.1)",
        border: "1px solid rgba(255,255,255,0.2)",
        color: "white",
        padding: "10px 20px",
        borderRadius: "20px",
        cursor: "pointer",
    },
    avoid: {
        color: "#be185d",
        fontSize: "14px",
        marginTop: "10px",
        maxWidth: "380px",
        lineHeight: "1.5",
        fontWeight: "bold",
    },

    tempo: {
        color: "#fde68a",
        fontSize: "14px",
        marginTop: "6px",
        maxWidth: "380px",
        lineHeight: "1.5",
        marginBottom: "20px"
    },
    detailContent: {
        width: "100%",
        display: "flex",
        justifyContent: "center", // 👈 додај
        alignItems: "center",
        gap: "20px",
        maxWidth: "1200px",
    },
    hint: {
        position: "absolute",
        bottom: "70px",
        fontSize: "15px",
        fontWeight: "bold",
        opacity: 0.5,
        color: "white",
        pointerEvents: "none"
    }
};