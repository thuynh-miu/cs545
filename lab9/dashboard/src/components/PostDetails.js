import { useState, useEffect, useMemo } from "react"
import { useNavigate, useParams } from "react-router";
import { fetchService } from "../services/fetchServices"
import Comments from "./Comments"
import './PostDetails.css'

const PostDetails = (props) => {
    const params = useParams()
    const navigate = useNavigate();

    const [postDetail, setPostDetail] = useState({
        comments: []
    })

    const fetchPost = async () => {
        if (!params.id) return;

        try {
            const post = await fetchService.getById("posts", params.id)
            if (post) {
                setPostDetail(post)
            }
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        fetchPost()
    }, [params.id])

    const memoizedComments = useMemo(() => {
        return <Comments comments={postDetail.comments} />
    }, [params.id])

    return (
        <div className="postDetails">
            <div className="postDetailsTitle">
                <p className="postDetailTitleText">{postDetail.title}</p>
                <p>{postDetail.author}</p>
            </div>
            <div className="postDetailsContent">
                <p>{postDetail.content}</p>
            </div>

            {memoizedComments}

            <div className="postDetailsButton">
                <button className="buttonUnderline" type="submit">{'Edit'}</button>
                <button className="buttonUnderline" type="submit">{'Delete'}</button>
            </div>
        </div>
    )
}

export default PostDetails