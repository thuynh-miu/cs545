import { Route, Routes, Navigate } from "react-router";
import AddPost from "../../components/AddPost";
import PostDetails from "../../components/PostDetails";
import Posts from "../../components/Posts";


export default function PageRoutes(props) {
    return (
        <Routes>
            <Route path="/" element={<Navigate replace to="/posts" />} />
            <Route path="posts" element={<Posts />}>
                <Route path=":id" element={<PostDetails />} />
            </Route>
            <Route path="add-post" element={<AddPost />} />
        </Routes>
    );
}


