export default function LeftSide() {
    return (
        <div style={styles.container}>
            <div style={styles.inner}>

                <p style={styles.badge}>FORM CHECK</p>

                <h1 style={styles.title}>
                    Meet <span style={styles.highlight}>Sasha</span> 👋
                </h1>

                <p style={styles.subtitle}>
                    Your 3D fitness coach that helps you move better, train smarter and avoid injuries.
                </p>

                <div style={styles.features}>
                    <Feature icon="🎯" text="Master the correct technique for every exercise" />
                    <Feature icon="🧍‍♀️" text="Visualize movements clearly in 3D" />
                    <Feature icon="⚡" text="Understand exactly which muscles are working" />
                    <Feature icon="💡" text="Tap on body parts to explore exercises" />
                </div>

            </div>
        </div>
    );
}

function Feature({ icon, text }) {
    return (
        <div style={styles.featureItem}>
            <span style={styles.icon}>{icon}</span>
            <p style={styles.featureText}>{text}</p>
        </div>
    );
}

const styles = {
    container: {
        flex: 1,
        display: "flex",
        justifyContent: "center", // вертикален центар
        alignItems: "center",     // хоризонтален центар
        padding: "60px 80px",
        height: "100%",
        boxSizing: "border-box",
        background: "linear-gradient(to bottom, #fdf2f8, #fbcfe8)",
    },

    inner: {
        maxWidth: "480px",
        width: "100%",
    },

    badge: {
        fontSize: "11px",
        letterSpacing: "3px",
        color: "#ec4899",
        marginBottom: "12px",
        fontWeight: "600",
        textAlign: "center",
    },

    title: {
        fontSize: "52px",
        fontWeight: "700",
        marginBottom: "18px",
        color: "#1e293b",
        lineHeight: "1.1",
        textAlign: "center",
    },

    highlight: {
        color: "#ec4899",
    },

    subtitle: {
        fontSize: "18px",
        color: "#475569",
        marginBottom: "35px",
        lineHeight: "1.6",
        textAlign: "center",
    },

    features: {
        display: "flex",
        flexDirection: "column",
        gap: "16px",
    },

    featureItem: {
        display: "flex",
        alignItems: "center",
        gap: "14px",
        justifyContent: "center"
    },

    featureText: {
        color: "#334155",
        fontSize: "15px",
        // textAlign: "center",
    },

    icon: {
        fontSize: "18px",
    },
};