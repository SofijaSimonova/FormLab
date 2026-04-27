import LeftSide from "../components/LeftSide";
import RightSide from "../components/RightSide";

export default function HomePage() {
    return (
        <div style={styles.container}>
            <div style={{ flex: 1 }}>
                <LeftSide />
            </div>

            <div style={{ flex: 1 }}>
                <RightSide />
            </div>
        </div>
    );
}

const styles = {
    container: {
        display: "flex",
        height: "100vh",
        overflow: "hidden",
        width: "100%"
    },
};