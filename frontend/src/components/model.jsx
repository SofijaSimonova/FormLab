import { useGLTF, useAnimations } from "@react-three/drei";
import { useEffect, useRef } from "react";
import { LoopOnce, LoopRepeat } from "three";
import { useFrame } from "@react-three/fiber";

export default function Model({ onAnimationEnd, rotationY = 0, playAnimation = true, animationName, scale = 0.3, }) {
    const group = useRef();
    const { scene, animations } = useGLTF("/models/FormLabTrainer.glb");
    const { actions } = useAnimations(animations, group);

    useEffect(() => {
        if (!actions || !animations) return;

        console.log("actions:", Object.keys(actions));
        console.log("requested:", animationName);
        console.log("animationName:", animationName);

        let action;
        let isDefault = false;

        if (animationName && actions[animationName]) {
            action = actions[animationName];
        } else {
            const firstClip = animations[0];
            action = actions[firstClip.name];
            isDefault = true;
        }

        if (!action) return;

        Object.values(actions).forEach(a => a.stop());

        action.reset();

        if (isDefault) {

            action.reset();
            action.setLoop(LoopOnce, 1);
            action.clampWhenFinished = true;

            const mixer = action.getMixer();

            if (!playAnimation) {
                action.play();
                mixer.update(100);
                action.paused = true;
                return;
            }

            const onFinish = () => {
                onAnimationEnd && onAnimationEnd();
            };

            mixer.addEventListener("finished", onFinish);

            action.play();

            return () => mixer.removeEventListener("finished", onFinish);
        } else {
            action.setLoop(LoopRepeat, Infinity);
        }

        if (!playAnimation) return;

        action.play();

    }, [actions, animationName, playAnimation, animations]);

    useFrame(() => {
        if (!group.current) return;
        group.current.rotation.y +=
            (rotationY - group.current.rotation.y) * 0.08;
    });

    return (
        <primitive
            ref={group}
            object={scene}
            scale={scale}
            position={[0, -1.2, 0]}
        />
    );
}