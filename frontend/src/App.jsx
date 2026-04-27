import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import ExercisesPage from "./pages/ExercisesPage";

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/exercises/:muscle" element={<ExercisesPage />} />
        </Routes>
    );
}