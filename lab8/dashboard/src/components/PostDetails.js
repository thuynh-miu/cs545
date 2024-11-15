import { useState, useEffect, useContext, useMemo } from "react"
import { fetchService } from "../services/fetchServices"
import Comments from "./Comments"
import './PostDetails.css'
import { PostContext } from "../context/PostContext"

const PostDetails = (props) => {
    const postContext = useContext(PostContext)

    const [postDetail, setPostDetail] = useState({
        comments: []
    })

    const fetchPost = async () => {
        if (!postContext.id) return;

        try {
            const post = await fetchService.getById("posts", postContext.id)
            if (post) {
                setPostDetail(post)
            }
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        fetchPost()
    }, [postContext.id])

    const memoizedComments = useMemo(() => {
        return <Comments comments={postDetail.comments} />
    }, [postContext.id])

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